inherit core-image

export IMAGE_BASENAME = "hype-installer"

IMAGE_INSTALL = " \
    hype-installer-init \
    busybox \
    udev \
    base-passwd \
    packagegroup-tpm \
    coreutils \
    grub \
    syslinux \
    syslinux-mbr \
    syslinux-extlinux \
    tboot-utils \
    tboot-lcptools \
    e2fsprogs \
    e2fsprogs-mke2fs \
    rsync \
    lvm2 \
    cryptsetup \
    lsbinitscripts \
    "

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

IMAGE_ROOTFS_SIZE = "8192"

TARGET_INITRD_IMAGE = "hype-initramfs"
TARGET_INITRD = "${DEPLOY_DIR_IMAGE}/${TARGET_INITRD_IMAGE}-${MACHINE}.cpio.gz"

TARGET_ROOTFS_IMAGE = "hype-rootfs"
TARGET_ROOTFS = "${DEPLOY_DIR_IMAGE}/${TARGET_ROOTFS_IMAGE}-${MACHINE}.ext3"

INITRD = "${DEPLOY_DIR_IMAGE}/${IMAGE_BASENAME}-${MACHINE}.cpio.gz"

NOHDD = "1"

do_rootfs[depends] += "${TARGET_INITRD_IMAGE}:do_rootfs"
do_rootfs[depends] += "${TARGET_ROOTFS_IMAGE}:do_rootfs"

XEN = "${DEPLOY_DIR_IMAGE}/xen-${MACHINE}.gz"
TBOOT = "${DEPLOY_DIR_IMAGE}/tboot-${MACHINE}.gz"
ACM = "${DEPLOY_DIR_IMAGE}/acm_*.bin"
MBOOT = "${STAGING_DATADIR}/syslinux/mboot.c32"
KERNEL = "${DEPLOY_DIR_IMAGE}/bzImage-${MACHINE}.bin"

# ROOTFS_POSTPROCESS_COMMAND will execute with the context of finalizing the initramfs located at ${IMAGE_ROOTFS}.
ROOTFS_POSTPROCESS_COMMAND += "echo "127.0.0.1	localhost" > ${IMAGE_ROOTFS}/${sysconfdir}/hosts;"

# Use this to include installer components into initramfs.	
ROOTFS_POSTPROCESS_COMMAND += "install -m 0755 -d ${IMAGE_ROOTFS}/installer;"
ROOTFS_POSTPROCESS_COMMAND += "install -m 0644 ${TARGET_ROOTFS} ${IMAGE_ROOTFS}/installer/rootfs.img;"
ROOTFS_POSTPROCESS_COMMAND += "install -m 0644 ${KERNEL} ${IMAGE_ROOTFS}/installer/vmlinuz;"
ROOTFS_POSTPROCESS_COMMAND += "install -m 0644 ${TARGET_INITRD} ${IMAGE_ROOTFS}/installer/initrd;"
ROOTFS_POSTPROCESS_COMMAND += "install -m 0644 ${MBOOT} ${IMAGE_ROOTFS}/installer/mboot.c32;"
ROOTFS_POSTPROCESS_COMMAND += "install -m 0644 ${TBOOT} ${IMAGE_ROOTFS}/installer/tboot.gz;"
ROOTFS_POSTPROCESS_COMMAND += "install -m 0644 ${XEN} ${IMAGE_ROOTFS}/installer/xen.gz;"
ROOTFS_POSTPROCESS_COMMAND += "install -m 0644 ${ACM} ${IMAGE_ROOTFS}/installer/;"

# Build syslinux config used for target install, not to be confused with isolinux.cfg in build_syslinux_cfg()
#ROOTFS_POSTPROCESS_COMMAND += "echo "ALLOWOPTIONS 1" > ${IMAGE_ROOTFS}/installer/syslinux.cfg;"
#ROOTFS_POSTPROCESS_COMMAND += "echo "DEFAULT boot" >> ${IMAGE_ROOTFS}/installer/syslinux.cfg;"
#ROOTFS_POSTPROCESS_COMMAND += "echo "TIMEOUT 10" >> ${IMAGE_ROOTFS}/installer/syslinux.cfg;"
#ROOTFS_POSTPROCESS_COMMAND += "echo "PROMPT 1" >> ${IMAGE_ROOTFS}/installer/syslinux.cfg;"
#ROOTFS_POSTPROCESS_COMMAND += "echo "LABEL boot" >> ${IMAGE_ROOTFS}/installer/syslinux.cfg;"
#ROOTFS_POSTPROCESS_COMMAND += "echo "  KERNEL mboot.c32" >> ${IMAGE_ROOTFS}/installer/syslinux.cfg;"
#ROOTFS_POSTPROCESS_COMMAND += "echo "  APPEND /tboot.gz logging=serial,vga,memory --- /xen.gz loglvl=all guest_loglvl=all console=com1,vga com1=115200,8n1 --- /vmlinuz ramdisk_size=32768 root=/dev/ram0 rw rootimg=rootfs.img rootimgpcr=9 console=hvc0 earlyprintk=xen console=tty0 panic=10 --- /initrd --- /acm_snb.bin --- /acm_ivb.bin" >> ${IMAGE_ROOTFS}/installer/syslinux.cfg;"

# Pre-requisite modules needed for isolinux.
syslinux_iso_populate_append() {
	install -m 0444 ${STAGING_DATADIR}/syslinux/mboot.c32 ${ISODIR}${ISOLINUXDIR}
}

# Populate modules used to boot.
populate_append() {
	install -m 0644 ${DEPLOY_DIR_IMAGE}/tboot-${MACHINE}.gz ${DEST}/tboot.gz
	install -m 0644 ${DEPLOY_DIR_IMAGE}/xen-${MACHINE}.gz ${DEST}/xen.gz
	install -m 0644 ${DEPLOY_DIR_IMAGE}/acm_*.bin ${DEST}/
}

# Build isolinux.cfg.
build_syslinux_cfg () {
	echo "ALLOWOPTIONS 1" > ${SYSLINUXCFG}
	echo "DEFAULT boot" >> ${SYSLINUXCFG}
	echo "TIMEOUT 10" >> ${SYSLINUXCFG}
	echo "PROMPT 1" >> ${SYSLINUXCFG}
	echo "LABEL boot" >> ${SYSLINUXCFG}
	echo "  KERNEL mboot.c32" >> ${SYSLINUXCFG}
	echo "  APPEND /tboot.gz logging=serial,vga,memory --- /xen.gz loglvl=all guest_loglvl=all console=com1,vga com1=115200,8n1 --- /vmlinuz ramdisk_size=32768 root=/dev/ram0 rw rootimg=rootfs.img rootimgpcr=9 console=hvc0 earlyprintk=xen console=tty0 panic=10 --- /initrd --- /acm_snb.bin --- /acm_ivb.bin" >> ${SYSLINUXCFG}
}
