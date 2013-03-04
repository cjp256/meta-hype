
require ${TOPDIR}/../meta-measured/recipes-tpm/images/core-image-tpm-initramfs.bb

export IMAGE_BASENAME = "hype-initramfs"

IMAGE_INSTALL = " \
    hype-initramfs-init \
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
