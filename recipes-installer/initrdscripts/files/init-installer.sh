#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin
ROOT_MOUNT=/mnt/root
ROOT_IMAGE_PCR=9
IP=$(which ip)
LN=$(which ln)
MKDIR=$(which mkdir)
MKNOD=$(which mknod)
MKTEMP=$(which mktemp)
MODPROBE=$(which modprobe)
MOUNT=$(which mount)
SLEEP=$(which sleep)

[ -z "$CONSOLE" ] && CONSOLE="/dev/console"

# debug logging failure sequence
#   give access to a shell
fatal() {
    echo $1 >$CONSOLE
    echo >$CONSOLE
    exec sh
}

# production logging failure sequence (no shell)
fatal_prod() {
    echo $1 >$CONSOLE
    echo >$CONSOLE
}

# sanity
[ ! -x $IP ]       && fatal "No ip command."
[ ! -x $LN ]       && fatal "No ln command."
[ ! -x $MKDIR ]    && fatal "No mkdir command."
[ ! -x $MKNOD ]    && fatal "No mknod command."
[ ! -x $MKTEMP ]   && fatal "No mktemp command."
[ ! -x $MODPROBE ] && fatal "No modprobe command."
[ ! -x $MOUNT ]    && fatal "No mount command."
[ ! -x $SLEEP ]    && fatal "No sleep command."

early_setup () {
    $MKDIR /proc /sys
    $MOUNT -t proc proc /proc
    $MOUNT -t sysfs sysfs /sys

    $MKDIR /tmp /run
    $LN -s /run /var/run
}

tss_setup () {
    $MODPROBE tpm-tis
    # tcsd needs loopback networking
    if ! $IP addr add 127.0.0.1/8 dev lo ; then
        fatal "failed to give loopback a localhost address"
    fi
    if ! $IP link set up dev lo ; then
        fatal "failed to bring up loopback device"
    fi
    # get udev rules executed. we care about tpm & removable media
    # touch functions since current dev requires it and DNE
    touch /etc/init.d/functions
    chmod +x /etc/init.d/functions
    if ! /etc/init.d/udev start ; then
        fatal "failed to start udev"
    fi
    # run tcsd
    if ! /etc/init.d/trousers start ; then
        echo "failed to start tcsd"; sleep 2
    fi
}

choose_device() {
    # choose which device to install to
    while [ 1 ]; do
        dev_list=$(find /sys/block/ -maxdepth 1 -name '[sh]d*')

        echo "devices available: $dev_list"

        read -p "enter device to install to: ($DEFAULT_DEVICE) " DST_DEVICE

        if [ -z "$DST_DEVICE" ]; then
            DST_DEVICE=$DEFAULT_DEVICE
        fi
        
        if [ -b "/dev/$DST_DEVICE" ]; then 
            echo "/dev/$DST_DEVICE exists..."
            break
        fi
        
        echo "invalid block device /dev/$DST_DEVICE..."
        
        if [ ! -f "/sys/block/$DST_DEVICE/device/model" ]; then
            echo "does not exist: /sys/block/$DST_DEVICE/device/model"
        fi            
                
        echo "invalid device specified: $DST_DEVICE"
        
        if [ "$DST_DEVICE" == "quit" ]; then
            echo "bailing..."
            break
        fi
    done
}

choose_device_for_sure() {
    while [ 1 ]; do
        choose_device
        
        if [ "$DST_DEVICE" == "quit" ]; then
            echo "bailing..."
            break
        fi
        
        let x=$(cat "/sys/block/$DST_DEVICE/size")
        let xgb=$x*512/1024/1024
        
        echo "you chose:"
        echo "  device: $DST_DEVICE"
        echo "  model: $(cat /sys/block/$DST_DEVICE/device/model)"
        echo "  size: $xgb GiB"
        
        read -p "are you sure? (y/n) " yn
        
        if [ "$yn" == "y" ]; then
            break;
        fi
        
        echo "invalid answer..."
    done
}

upgrade_rootfs() {
    while [ 1 ]; do

        read -p "attempt to upgrade instead of fresh install? (y/n) " yn

        if [ "$yn" == "y" ]; then
	    return 0
        fi

	if [ "$yn" == "n" ]; then
	    return 1
        fi

        echo "invalid answer..."
    done
}

freshen_up() {
    # sync and trigger udev
    sync
    sleep 1
    udevadm trigger
    sleep 1
}

upgrade_check() {
	echo "scanning logical volumes..."
	vgscan
	lvscan
	if ! lvchange -a y dom0; then
		echo "invalid upgrade target - dom0 volumes not found!"
		return 1
	fi

	return 0
}

format_disk() {
	# nuke mbr/partition table
	echo "nuking partition table and mbr..."
	dd if=/dev/zero of=$DEVICE bs=512 count=1

	# push mbr
	dd if=/usr/lib/syslinux/mbr.bin of=$DEVICE

	# create lvm partition
	fdisk -u $DEVICE <<EOF
	n
	p
	1
	2048

	t
	8e

	w
EOF

	# freshen up
	freshen_up

	# create lvm physical volume and volume group
	echo "creating lvm partitions..."
	pvcreate -ff -y $LVM_PARTITION
	vgcreate dom0 $LVM_PARTITION
	vgscan

	# create the logical volumes
	lvcreate --name boot --size $DOM0_BOOT_PARTITION_SIZE dom0
	lvcreate --name secure --size $DOM0_SECURE_PARTITION_SIZE dom0
	lvcreate --name storage -l $DOM0_STORAGE_PARTITION_SIZE dom0

	# format all these wonderful volumes...
	echo "formatting partitions..."
	mkfs.ext4 /dev/mapper/dom0-boot
	mkfs.ext4 /dev/mapper/dom0-storage

	# TODO: create /secure encrypted partition
	#mkfs.ext4 /dev/mapper/dom0-secure
}

set -x

DOM0_BOOT_PARTITION_SIZE="128M"
DOM0_SECURE_PARTITION_SIZE="128M"
DOM0_STORAGE_PARTITION_SIZE="100%FREE"
DEFAULT_DEVICE="sda"
DEVICE=""

echo "welcome to the installer!"

# XXX: bug workarounds
rm -rf /var/lock
mkdir -p /var/lock/lvm

# remove automount rules
rm -f /etc/udev/rules.d/automount.rules

early_setup
tss_setup

# populate /dev
mount -t devtmpfs devtmpfs $rootmnt/dev

# find me some mtab
ln -sf /proc/mounts /etc/mtab

# figure out where to install
choose_device_for_sure

# really, you want to bail out now??
if [ "$DST_DEVICE" == "quit" ]; then
    sh -i
fi

DEVICE="/dev/$DST_DEVICE"

LVM_PARTITION="$DEVICE"1

while [ 1 ]; do
	# TODO: check if this a candidate for upgrade first
	if ! upgrade_rootfs; then
		# not upgrading, format disk and proceed
		format_disk
		break
	fi

	if upgrade_check; then
		# upgrade check passed, proceed
		break
	fi
done

# mount boot partition for installation
mkdir -p /mnt/boot
mount -t ext4 /dev/mapper/dom0-boot /mnt/boot

# mount storage partition
mkdir -p /mnt/storage
mount -t ext4 /dev/mapper/dom0-storage /mnt/storage

# move rootfs into /storage
mv /installer/rootfs.img /mnt/storage/rootfs.img

# move over boot modules
mv /installer/* /mnt/boot/

# grub-install gets awfully confused - build the device.map
mkdir -p /boot/grub
mkdir -p /mnt/boot/grub
echo "(hd0) $DEVICE" > /boot/grub/device.map
echo "(hostdisk//dev/mapper/dom0-boot) /dev/mapper/dom0-boot" >> /boot/grub/device.map
cp /boot/grub/device.map /mnt/boot/grub/device.map

# install grub to disk
grub-install --modules="lvm part_msdos" --boot-directory=/mnt/boot /dev/sda

# copy grub config into place
cp /grub.cfg /mnt/boot/grub/grub.cfg

# cleanup
umount /mnt/storage
umount /mnt/boot

sync

echo "installation complete! rebooting..."
sleep 5

# reboot
reboot -f

