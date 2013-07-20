# measured, read-only rootfs

include ${TOPDIR}/../meta-measured/recipes-tpm/images/core-image-tpm.inc

IMAGE_INSTALL += "\
    tboot-utils \
    rsync \
    lvm2 \
    cryptsetup \
    lsbinitscripts \
    xen-base \
"

