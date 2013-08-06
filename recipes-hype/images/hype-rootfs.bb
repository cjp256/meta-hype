# measured, read-only rootfs

require recipes-tpm/images/core-image-tpm.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

IMAGE_INSTALL += "\
    tboot-utils \
    rsync \
    lvm2 \
    cryptsetup \
    lsbinitscripts \
    xen-base \
    xen-qemu \
    qemu \
"

# Tidy up bits not needed in the image...

# /boot is taken care of via installer, handled outside hype-rootfs
ROOTFS_POSTPROCESS_COMMAND += "rm -rf ${IMAGE_ROOTFS}/boot/*; "
