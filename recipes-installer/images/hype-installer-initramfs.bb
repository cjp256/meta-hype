
require ${TOPDIR}/meta-measured/recipes-tpm/images/core-image-tpm-initramfs.bb

export IMAGE_BASENAME = "hype-installer-initramfs"

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
