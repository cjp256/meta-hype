DESCRIPTION = "open source processor emulator"
HOMEPAGE = "http://qemu.org"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=441c28d2cf86e15a37fa47e15a72fbac \
                    file://COPYING.LIB;endline=24;md5=c04def7ae38850e7d3ef548588159913"

SRC_URI_prepend = "http://wiki.qemu.org/download/qemu-${PV}.tar.bz2"
SRC_URI[md5sum] = "04b94189ba587b3280713ded3b7f959a"
SRC_URI[sha256sum] = "f661147d190ab8432045058a660d810f13dc528fe7017ce578e9f2da2997a250"

INSANE_SKIP_${PN} = "arch"

DEPENDS = "util-linux util-linux-native file-native zlib ncurses openssl bison-native flex-native gettext dev86-native iasl-native pciutils virtual/libgl virtual/libsdl bridge-utils iproute2 procps yajl pixman python xz-native xen"

inherit autotools

do_configure() {
	# TODO: --enable-spice --enable-tcg-interpreter --enable-libusb --enable-usb-redir --enable-tpm
	${S}/configure --prefix=${prefix} --sysconfdir=${sysconfdir} --libexecdir=${libexecdir} --disable-kvm --enable-xen --enable-xen-pci-passthrough --target-list="i386-softmmu x86_64-softmmu"
	test ! -e ${S}/target-i386/beginend_funcs.sh || chmod a+x ${S}/target-i386/beginend_funcs.sh
}

do_install () {
	export STRIP="true"
	autotools_do_install
	install -d ${D}${datadir}/qemu
}
