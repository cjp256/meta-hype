# measured, read-only rootfs

include core-image-tpm.inc

IMAGE_INSTALL += "\
    tboot-utils \
    rsync \
    lvm2 \
    cryptsetup \
    lsbinitscripts \
    xen-base \
"

# Tidy up bits not needed in the image...

# /boot is taken care of via installer, handled outside hype-rootfs
ROOTFS_POSTPROCESS_COMMAND += "rm -rf ${IMAGE_ROOTFS}/boot/*; "
