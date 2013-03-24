# measured, read-only rootfs

require ${TOPDIR}/../meta-measured/recipes-tpm/images/core-image-tpm.bb

IMAGE_INSTALL += "\
    tboot-utils \
    rsync \
    lvm2 \
    cryptsetup \
"
