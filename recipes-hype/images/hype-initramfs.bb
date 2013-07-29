
require ${TOPDIR}/../meta-measured/recipes-tpm/images/core-image-tpm-initramfs.bb

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

export IMAGE_BASENAME = "hype-initramfs"

IMAGE_INSTALL = " \
    initramfs-boot-hype \
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
    lsbinitscripts \
    "
