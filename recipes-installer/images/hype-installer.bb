inherit core-image

export IMAGE_BASENAME = "hype-installer"

IMAGE_INSTALL = " \
    hype-installer-init \
    busybox \
    udev \
    base-passwd \
    packagegroup-tpm \
    coreutils \
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
    "

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

IMAGE_ROOTFS_SIZE = "8192"

INITRD_IMAGE = "hype-initramfs"
INITRD = "${DEPLOY_DIR_IMAGE}/${INITRD_IMAGE}-${MACHINE}.cpio.gz"

ROOTFS_IMAGE = "hype-rootfs"
ROOTFS = "${DEPLOY_DIR_IMAGE}/${ROOTFS_IMAGE}-${MACHINE}.ext3"

NOHDIMG = "1"

do_rootfs[depends] += "${INITRD_IMAGE}:do_rootfs"
do_rootfs[depends] += "${ROOTFS_IMAGE}:do_rootfs"

ROOTFS_POSTPROCESS_COMMAND += "echo "127.0.0.1	localhost" > ${IMAGE_ROOTFS}/${sysconfdir}/hosts;"

TBOOT = "${DEPLOY_DIR_IMAGE}/tboot-${MACHINE}.gz"
ACM = "${DEPLOY_DIR_IMAGE}/acm_*.bin"
MBOOT = "${STAGING_LIBDIR}/syslinux/mboot.c32"
KERNEL = "${DEPLOY_DIR_IMAGE}/bzImage-${MACHINE}.bin"

# put installer pieces into play
ROOTFS_POSTPROCESS_COMMAND += "mkdir -p ${IMAGE_ROOTFS}/installer;"
ROOTFS_POSTPROCESS_COMMAND += "install -m 0644 ${ROOTFS} ${IMAGE_ROOTFS}/installer/rootfs.img;"
ROOTFS_POSTPROCESS_COMMAND += "install -m 0644 ${KERNEL} ${IMAGE_ROOTFS}/installer/vmlinuz;"
ROOTFS_POSTPROCESS_COMMAND += "install -m 0644 ${INITRD} ${IMAGE_ROOTFS}/installer/initrd;"
ROOTFS_POSTPROCESS_COMMAND += "install -m 0644 ${MBOOT} ${IMAGE_ROOTFS}/installer/mboot.c32;"
ROOTFS_POSTPROCESS_COMMAND += "install -m 0644 ${TBOOT} ${IMAGE_ROOTFS}/installer/tboot.gz;"
ROOTFS_POSTPROCESS_COMMAND += "install -m 0644 ${ACM} ${IMAGE_ROOTFS}/installer/;"

# build syslinux config
ROOTFS_POSTPROCESS_COMMAND += "echo "ALLOWOPTIONS 1" > ${IMAGE_ROOTFS}/installer/syslinux.cfg;"
ROOTFS_POSTPROCESS_COMMAND += "echo "DEFAULT boot" >> ${IMAGE_ROOTFS}/installer/syslinux.cfg;"
ROOTFS_POSTPROCESS_COMMAND += "echo "TIMEOUT 10" >> ${IMAGE_ROOTFS}/installer/syslinux.cfg;"
ROOTFS_POSTPROCESS_COMMAND += "echo "PROMPT 1" >> ${IMAGE_ROOTFS}/installer/syslinux.cfg;"
ROOTFS_POSTPROCESS_COMMAND += "echo "LABEL boot" >> ${IMAGE_ROOTFS}/installer/syslinux.cfg;"
ROOTFS_POSTPROCESS_COMMAND += "echo "  KERNEL mboot.c32" >> ${IMAGE_ROOTFS}/installer/syslinux.cfg;"
ROOTFS_POSTPROCESS_COMMAND += "echo "  APPEND /tboot.gz logging=serial,vga,memory --- /vmlinuz ramdisk_size=32768 root=/dev/ram0 rw rootimg=rootfs.img rootimgpcr=9 console=ttyS0,115200n8 console=tty0 --- /initrd --- /acm_snb.bin --- /acm_ivb.bin" >> ${IMAGE_ROOTFS}/installer/syslinux.cfg;"
	
syslinux_iso_populate_append() {
	install -m 0444 ${STAGING_LIBDIR}/syslinux/mboot.c32 ${ISODIR}${ISOLINUXDIR}
}

# have bootimg populate function grab tboot and ACM
populate_append() {
	install -m 0644 ${DEPLOY_DIR_IMAGE}/tboot-${MACHINE}.gz ${DEST}/tboot.gz
	install -m 0644 ${DEPLOY_DIR_IMAGE}/acm_*.bin ${DEST}/
}

build_syslinux_cfg () {
	echo "ALLOWOPTIONS 1" > ${SYSLINUXCFG}
	echo "DEFAULT boot" >> ${SYSLINUXCFG}
	echo "TIMEOUT 10" >> ${SYSLINUXCFG}
	echo "PROMPT 1" >> ${SYSLINUXCFG}
	echo "LABEL boot" >> ${SYSLINUXCFG}
	echo "  KERNEL mboot.c32" >> ${SYSLINUXCFG}
	echo "  APPEND /tboot.gz logging=serial,vga,memory --- /vmlinuz ramdisk_size=32768 root=/dev/ram0 rw rootimg=rootfs.img rootimgpcr=9 console=ttyS0,115200n8 console=tty0 --- /initrd --- /acm_snb.bin --- /acm_ivb.bin" >> ${SYSLINUXCFG}
}
