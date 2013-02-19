DESCRIPTION = "Setup virtual encryption devices under dm-crypt Linux"
HOMEPAGE = "http://code.google.com/p/cryptsetup/"
SECTION = "console"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=32107dd283b1dfeb66c9b3e6be312326"

DEPENDS = "util-linux libgcrypt popt lvm2"
RRECOMMENDS_${PN} = "kernel-module-aes \
                     kernel-module-dm-crypt \
                     kernel-module-md5 \
                     kernel-module-cbc \
                     kernel-module-sha256 \
                    "
PR = "r0"

SRC_URI = "http://cryptsetup.googlecode.com/files/cryptsetup-${PV}.tar.bz2"
SRC_URI[md5sum] = "99002ac59a65ea371e7a98200943cb80"
SRC_URI[sha256sum] = "dd9686fce5d3276b2eb2ac40d513a9b64850af8fff881442f2cfe87257ba2406"

inherit autotools gettext
