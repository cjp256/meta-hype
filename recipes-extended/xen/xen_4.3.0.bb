DESCRIPTION = "Xen hypervisor" 
HOMEPAGE = "http://xen.org"
LICENSE = "GPLv2"
SECTION = "console/tools"
PR = "r0"

LIC_FILES_CHKSUM = "file://COPYING;md5=e0f0f3ac55608719a82394cc353928df"

SRC_URI = "http://bits.xensource.com/oss-xen/release/${PV}/xen-${PV}.tar.gz"

SRC_URI[md5sum] = "7b18cfb58f1ac2ce39cf35a1867f0c0a"
SRC_URI[sha256sum] = "e1e9faabe4886e2227aacdbde74410653b233d66642ca1972a860cbec6439961"

S = "${WORKDIR}/xen-${PV}"

COMPATIBLE_HOST = '(x86_64.*).*-linux'

inherit autotools gettext setuptools update-rc.d

DEPENDS = "util-linux util-linux-native file-native zlib ncurses openssl bison-native flex-native gettext dev86-native iasl-native pciutils virtual/libgl virtual/libsdl bridge-utils iproute2 procps yajl pixman python xz-native"

RDEPENDS_xen-base = "\
	libgcc pciutils bridge-utils iproute2 util-linux udev procps bash coreutils python python-core python-shell python-pprint perl xz \
	${PN}-blktap \
	${PN}-console \
	${PN}-libblktapctl \
	${PN}-libxenguest \
	${PN}-libxenlight \
	${PN}-libxenvchan \
	${PN}-libxenctrl \
	${PN}-libxlutil \
	${PN}-libvhd \
	${PN}-libxenstat \
	${PN}-libxenstore \
	${PN}-libblktap \
	${PN}-libfsimage \
	${PN}-flask \
	${PN}-fsimage \
 	${PN}-hvmloader \
	${PN}-scripts-block \
	${PN}-scripts-network \
	${PN}-xenpaging \
	${PN}-xen-watchdog \
	${PN}-xencommons \
	${PN}-xendomains \
	${PN}-xenstore \
	${PN}-xenstored \
	${PN}-xl \
	"

RDEPENDS_${PN}-scripts-block = "\
	${PN}-scripts-common \
	${PN}-udev \
	"

RDEPENDS_${PN}-scripts-network = "\
	bridge-utils \
	${PN}-scripts-common \
	${PN}-udev \
	"

PACKAGES = "\
	${PN}-base \
	${PN}-blktap \
	${PN}-console \
	${PN}-dbg \
	${PN}-dev \
	${PN}-doc \
	${PN}-flask \
	${PN}-fsimage \
	${PN}-gdbsx \
	${PN}-hvmloader \
	${PN}-hypervisor \
	${PN}-kdd \
	${PN}-libblktap \
	${PN}-libblktapctl \
	${PN}-libblktapctl-dev \
	${PN}-libblktap-dev \
	${PN}-libfsimage \
	${PN}-libfsimage-dev \
	${PN}-libvhd \
	${PN}-libvhd-dev \
	${PN}-libxenctrl \
	${PN}-libxenctrl-dev \
	${PN}-libxenguest \
	${PN}-libxenguest-dev \
	${PN}-libxenlight \
	${PN}-libxenlight-dev \
	${PN}-libxenstat \
	${PN}-libxenstat-dev \
	${PN}-libxenstore \
	${PN}-libxenstore-dev \
	${PN}-libxenvchan \
	${PN}-libxenvchan-dev \
	${PN}-libxlutil \
	${PN}-libxlutil-dev \
	${PN}-misc \
	${PN}-pygrub \
	${PN}-python \
	${PN}-qemu \
	${PN}-remus \
	${PN}-scripts-block \
	${PN}-scripts-common \
	${PN}-scripts-network \
	${PN}-staticdev \
	${PN}-udev \
	${PN}-volatiles \
	${PN}-xcutils \
	${PN}-xencommons \
	${PN}-xend \
	${PN}-xend-examples \
	${PN}-xendomains \
	${PN}-xenmon \
	${PN}-xenpaging \
	${PN}-xenpmd \
	${PN}-xenstat \
	${PN}-xenstore \
	${PN}-xenstored \
	${PN}-xentrace \
	${PN}-xen-watchdog \
	${PN}-xl \
	${PN}-xl-examples \
	${PN}-xm \
	${PN}-xm-examples \
	"

FILES_${PN}-dbg += "\
	/usr/lib/.debug \
	/usr/lib/xen/bin/.debug \
	/usr/lib/python2.7/site-packages/.debug \
	/usr/lib/python2.7/site-packages/xen/lowlevel/.debug \
	/usr/lib/fs/xfs/.debug \
	/usr/lib/fs/ufs/.debug \
	/usr/lib/fs/ext2fs-lib/.debug \
	/usr/lib/fs/fat/.debug \
	/usr/lib/fs/zfs/.debug \
	/usr/lib/fs/reiserfs/.debug \
	/usr/lib/fs/iso9660/.debug \
	/usr/sbin/.debug \
	/usr/libexec/.debug \
	/usr/bin/.debug \
	/usr/lib/python2.7/dist-packages/.debug \
	/usr/lib/python2.7/dist-packages/xen/lowlevel/.debug \
	"

FILES_${PN}-dev = "\
	/usr/include \
	"

FILES_${PN}-doc = "\
	/etc/xen/README \
	/etc/xen/README.incompatibilities \
	/usr/share/doc \
	/usr/share/man \
	"

FILES_${PN}-staticdev += "\
	/usr/lib/libblktapctl.a \
	/usr/lib/libxenguest.a \
	/usr/lib/libxenlight.a \
	/usr/lib/libxenvchan.a \
	/usr/lib/libxenctrl.a \
	/usr/lib/libxlutil.a \
	/usr/lib/libvhd.a \
	/usr/lib/libxenstat.a \
	/usr/lib/libxenstore.a \
	/usr/lib/libblktap.a \
	"

FILES_${PN}-volatiles = "\
	/var/log \
	/var/run \
	/var/lock \
	/var/volatile \
	"

FILES_${PN}-libblktapctl = "/usr/lib/libblktapctl.so.*"
FILES_${PN}-libblktapctl-dev = "/usr/lib/libblktapctl.so"

FILES_${PN}-libxenguest = "/usr/lib/libxenguest.so.*"
FILES_${PN}-libxenguest-dev = "/usr/lib/libxenguest.so"

FILES_${PN}-libxenlight = "/usr/lib/libxenlight.so.*"
FILES_${PN}-libxenlight-dev = "/usr/lib/libxenlight.so"

FILES_${PN}-libxenvchan = "/usr/lib/libxenvchan.so.*"
FILES_${PN}-libxenvchan-dev = "/usr/lib/libxenvchan.so"

FILES_${PN}-libxenctrl = "/usr/lib/libxenctrl.so.*"
FILES_${PN}-libxenctrl-dev = "/usr/lib/libxenctrl.so"

FILES_${PN}-libxlutil = "/usr/lib/libxlutil.so.*"
FILES_${PN}-libxlutil-dev = "/usr/lib/libxlutil.so"

FILES_${PN}-libvhd = "/usr/lib/libvhd.so.*"
FILES_${PN}-libvhd-dev = "/usr/lib/libvhd.so"

FILES_${PN}-libxenstat = "/usr/lib/libxenstat.so.*"
FILES_${PN}-libxenstat-dev = "/usr/lib/libxenstat.so"

FILES_${PN}-libxenstore = "/usr/lib/libxenstore.so.*"
FILES_${PN}-libxenstore-dev = "/usr/lib/libxenstore.so"

FILES_${PN}-libblktap = "/usr/lib/libblktap.so.*"
FILES_${PN}-libblktap-dev = "/usr/lib/libblktap.so"

FILES_${PN}-libfsimage = "/usr/lib/libfsimage.so.*"
FILES_${PN}-libfsimage-dev = "/usr/lib/libfsimage.so"

FILES_${PN}-fsimage = "/usr/lib/fs/*/*fsimage.so"

FILES_${PN}-hypervisor = "\
	/boot/xen-4.3.0.gz \
	/boot/xen-4.3.gz \
	/boot/xen-4.gz \
	/boot/xen.gz \
	/boot/xen-syms-4.3.0 \
	"

FILES_${PN}-base = "\
	/etc/default/volatiles/99_xen \
	/etc/default/xencommons \
	/etc/default/xendomains \
	/etc/udev/rules.d \
	/etc/udev/rules.d/xen-backend.rules \
	/etc/udev/rules.d/xend.rules \
	/etc/xen/auto \
	/etc/xen/cpupool \
	/etc/sysconfig/xendomains \
	/var/xen/dump \
	"

FILES_${PN}-blktap = "\
	/usr/sbin/blktapctrl \
	/usr/sbin/img2qcow \
	/usr/sbin/lock-util \
	/usr/sbin/qcow2raw \
	/usr/sbin/qcow-create \
	/usr/sbin/tap-ctl \
	/usr/sbin/tapdisk \
	/usr/sbin/tapdisk2 \
	/usr/sbin/tapdisk-client \
	/usr/sbin/tapdisk-diff \
	/usr/sbin/tapdisk-stream \
	/usr/sbin/td-util \
	/usr/sbin/vhd-update \
	/usr/sbin/vhd-util \
	"

FILES_${PN}-console = "\
	/usr/lib/xen/bin/xenconsole \
	/usr/sbin/xenconsoled \
	"

FILES_${PN}-flask = "\
	/usr/sbin/flask-get-bool \
	/usr/sbin/flask-getenforce \
	/usr/sbin/flask-label-pci \
	/usr/sbin/flask-loadpolicy \
	/usr/sbin/flask-set-bool \
	/usr/sbin/flask-setenforce \
	"

FILES_${PN}-gdbsx = "\
	/usr/sbin/gdbsx \
	"

INSANE_SKIP_${PN}-hvmloader = "arch"
FILES_${PN}-hvmloader = "\
	/usr/lib/xen/boot/hvmloader \
	"

FILES_${PN}-kdd = "\
	/usr/sbin/kdd \
	"

FILES_${PN}-misc = "\
	/usr/bin/xencons \
	/usr/bin/xencov_split \
	/usr/bin/xen-detect \
	/usr/lib/xen/bin/xenpvnetboot \
	/usr/sbin/gtracestat \
	/usr/sbin/gtraceview \
	/usr/sbin/xen-bugtool \
	/usr/sbin/xencov \
	/usr/sbin/xend \
	/usr/sbin/xenperf \
	/usr/sbin/xenpm \
	/usr/sbin/xsview \
	/usr/sbin/xenwatchdogd \
	/usr/sbin/xen-tmem-list-parse \
	/usr/sbin/xen-python-path \
	/usr/sbin/xen-ringwatch \
	/usr/sbin/xen-hptool \
	/usr/sbin/xen-hvmcrash \
	/usr/sbin/xen-hvmctx \
	/usr/sbin/xenlockprof \
	/usr/sbin/xen-lowmemd \
	"

FILES_${PN}-pygrub = "\
	/usr/bin/pygrub \
	/usr/lib/xen/bin/pygrub \
	"

FILES_${PN}-python = "\
	/usr/lib/python2.7 \
	"

INSANE_SKIP_${PN}-qemu = "arch"
FILES_${PN}-qemu = " \
	/usr/share/xen/qemu \
	/usr/lib/xen/bin/qemu-system-i386 \
	/usr/lib/xen/bin/qemu-system-x86_64 \
	/usr/lib/xen/bin/qemu-img \
	/usr/lib/xen/bin/qemu-nbd \
	/usr/lib/xen/bin/qemu-ga \
	/usr/lib/xen/bin/qemu-io \
	/usr/lib/xen/bin/qemu-dm \
	/usr/lib/xen/bin/virtfs-proxy-helper \
	/usr/libexec/qemu-bridge-helper \
	/usr/etc/qemu \
	/usr/etc/qemu/target-x86_64.conf \
	/usr/share/qemu-xen \
	/usr/share/qemu-xen/qemu \
	/usr/share/qemu-xen/qemu/bamboo.dtb \
	/usr/share/qemu-xen/qemu/pxe-pcnet.rom \
	/usr/share/qemu-xen/qemu/vgabios-vmware.bin \
	/usr/share/qemu-xen/qemu/pxe-eepro100.rom \
	/usr/share/qemu-xen/qemu/pxe-e1000.rom \
	/usr/share/qemu-xen/qemu/openbios-ppc \
	/usr/share/qemu-xen/qemu/multiboot.bin \
	/usr/share/qemu-xen/qemu/vgabios-cirrus.bin \
	/usr/share/qemu-xen/qemu/bios.bin \
	/usr/share/qemu-xen/qemu/vgabios-stdvga.bin \
	/usr/share/qemu-xen/qemu/palcode-clipper \
	/usr/share/qemu-xen/qemu/pxe-ne2k_pci.rom \
	/usr/share/qemu-xen/qemu/spapr-rtas.bin \
	/usr/share/qemu-xen/qemu/slof.bin \
	/usr/share/qemu-xen/qemu/vgabios-qxl.bin \
	/usr/share/qemu-xen/qemu/pxe-rtl8139.rom \
	/usr/share/qemu-xen/qemu/openbios-sparc64 \
	/usr/share/qemu-xen/qemu/pxe-virtio.rom \
	/usr/share/qemu-xen/qemu/kvmvapic.bin \
	/usr/share/qemu-xen/qemu/openbios-sparc32 \
	/usr/share/qemu-xen/qemu/petalogix-s3adsp1800.dtb \
	/usr/share/qemu-xen/qemu/sgabios.bin \
	/usr/share/qemu-xen/qemu/linuxboot.bin \
	/usr/share/qemu-xen/qemu/qemu-icon.bmp \
	/usr/share/qemu-xen/qemu/ppc_rom.bin \
	/usr/share/qemu-xen/qemu/vgabios.bin \
	/usr/share/qemu-xen/qemu/s390-zipl.rom \
	/usr/share/qemu-xen/qemu/petalogix-ml605.dtb \
	/usr/share/qemu-xen/qemu/keymaps \
	/usr/share/qemu-xen/qemu/keymaps/common \
	/usr/share/qemu-xen/qemu/keymaps/th \
	/usr/share/qemu-xen/qemu/keymaps/is \
	/usr/share/qemu-xen/qemu/keymaps/en-gb \
	/usr/share/qemu-xen/qemu/keymaps/ar \
	/usr/share/qemu-xen/qemu/keymaps/fr-be \
	/usr/share/qemu-xen/qemu/keymaps/ru \
	/usr/share/qemu-xen/qemu/keymaps/hu \
	/usr/share/qemu-xen/qemu/keymaps/de-ch \
	/usr/share/qemu-xen/qemu/keymaps/no \
	/usr/share/qemu-xen/qemu/keymaps/fr \
	/usr/share/qemu-xen/qemu/keymaps/pl \
	/usr/share/qemu-xen/qemu/keymaps/fr-ca \
	/usr/share/qemu-xen/qemu/keymaps/de \
	/usr/share/qemu-xen/qemu/keymaps/fr-ch \
	/usr/share/qemu-xen/qemu/keymaps/bepo \
	/usr/share/qemu-xen/qemu/keymaps/lv \
	/usr/share/qemu-xen/qemu/keymaps/ja \
	/usr/share/qemu-xen/qemu/keymaps/da \
	/usr/share/qemu-xen/qemu/keymaps/lt \
	/usr/share/qemu-xen/qemu/keymaps/hr \
	/usr/share/qemu-xen/qemu/keymaps/es \
	/usr/share/qemu-xen/qemu/keymaps/modifiers \
	/usr/share/qemu-xen/qemu/keymaps/sl \
	/usr/share/qemu-xen/qemu/keymaps/it \
	/usr/share/qemu-xen/qemu/keymaps/nl \
	/usr/share/qemu-xen/qemu/keymaps/fo \
	/usr/share/qemu-xen/qemu/keymaps/mk \
	/usr/share/qemu-xen/qemu/keymaps/pt-br \
	/usr/share/qemu-xen/qemu/keymaps/tr \
	/usr/share/qemu-xen/qemu/keymaps/sv \
	/usr/share/qemu-xen/qemu/keymaps/fi \
	/usr/share/qemu-xen/qemu/keymaps/en-us \
	/usr/share/qemu-xen/qemu/keymaps/et \
	/usr/share/qemu-xen/qemu/keymaps/nl-be \
	/usr/share/qemu-xen/qemu/keymaps/pt \
	/usr/bin/qemu-nbd-xen \
	/usr/bin/qemu-img-xen \
	"

FILES_${PN}-remus = "\
	/usr/bin/remus \
	"

FILES_${PN}-scripts-network = " \
	/etc/xen/scripts/network-bridge \
	/etc/xen/scripts/network-nat \
	/etc/xen/scripts/network-route \
	/etc/xen/scripts/qemu-ifup \
	/etc/xen/scripts/vif2 \
	/etc/xen/scripts/vif-bridge \
	/etc/xen/scripts/vif-common.sh \
	/etc/xen/scripts/vif-nat \
	/etc/xen/scripts/vif-openvswitch \
	/etc/xen/scripts/vif-route \
	/etc/xen/scripts/vif-setup \
	"

FILES_${PN}-scripts-block = " \
	/etc/xen/scripts/blktap \
	/etc/xen/scripts/block \
	/etc/xen/scripts/block-common.sh \
	/etc/xen/scripts/block-enbd \
	/etc/xen/scripts/block-iscsi \
	/etc/xen/scripts/block-nbd \
	/etc/xen/scripts/vscsi \
	"

FILES_${PN}-scripts-common = " \
	/etc/xen/scripts/external-device-migrate \
	/etc/xen/scripts/hotplugpath.sh \
	/etc/xen/scripts/locking.sh \
	/etc/xen/scripts/logging.sh \
	/etc/xen/scripts/xen-hotplug-cleanup \
	/etc/xen/scripts/xen-hotplug-common.sh \
	/etc/xen/scripts/xen-network-common.sh \
	/etc/xen/scripts/xen-script-common.sh \
	"

FILES_${PN}-udev = "/etc/udev"

FILES_${PN}-xcutils = "\
	/usr/lib/xen/bin/lsevtchn \
	/usr/lib/xen/bin/readnotes \
	/usr/lib/xen/bin/xc_restore \
	/usr/lib/xen/bin/xc_save \
	"

FILES_${PN}-xend-examples = "\
	/etc/xen/xend-config.sxp \
	/etc/xen/xend-pci-permissive.sxp \
	/etc/xen/xend-pci-quirks.sxp \
	"

FILES_${PN}-xenpaging = "\
	/usr/lib/xen/bin/xenpaging \
	/var/lib/xen/xenpaging \
	"

FILES_${PN}-xenpmd = "\
	/usr/sbin/xenpmd \
	"

FILES_${PN}-xenstat = "\
	/usr/sbin/xentop \
	"

FILES_${PN}-xenstore = "\
	/usr/bin/xenstore \
	/usr/bin/xenstore-chmod \
	/usr/bin/xenstore-control \
	/usr/bin/xenstore-exists \
	/usr/bin/xenstore-list \
	/usr/bin/xenstore-ls \
	/usr/bin/xenstore-read \
	/usr/bin/xenstore-rm \
	/usr/bin/xenstore-watch \
	/usr/bin/xenstore-write \
	"

FILES_${PN}-xenstored = "\
	/usr/sbin/xenstored \
	/var/lib/xenstored \
	"

FILES_${PN}-xentrace = "\
	/usr/bin/xentrace \
	/usr/bin/xentrace_format \
	/usr/bin/xentrace_setsize \
	/usr/lib/xen/bin/xenctx \
	"

FILES_${PN}-xl = "\
	/etc/bash_completion.d/xl.sh \
	/etc/xen/xl.conf \
	/usr/lib/xen/bin/libxl-save-helper \
	/usr/sbin/xl \
	"

FILES_${PN}-xl-examples = "\
	/etc/xen/xlexample.hvm \
	/etc/xen/xlexample.pvlinux \
	"

FILES_${PN}-xm-examples = "\
	/etc/xen/xmexample1 \
	/etc/xen/xmexample2 \
	/etc/xen/xmexample3 \
	/etc/xen/xmexample.hvm \
	/etc/xen/xmexample.hvm-stubdom \
	/etc/xen/xmexample.nbd \
	/etc/xen/xmexample.pv-grub \
	/etc/xen/xmexample.vti \
	"

FILES_${PN}-xenmon = "\
	/usr/sbin/xenbaked \
	/usr/sbin/xentrace_setmask \
	/usr/sbin/xenmon.py \
	"

FILES_${PN}-xm = "\
	/etc/xen/xm-config.xml \
	/usr/share/xen/create.dtd \
	/usr/sbin/xm \
	"

FILES_${PN}-xencommons += "/etc/init.d/xencommons"
FILES_${PN}-xend += "/etc/init.d/xend"
FILES_${PN}-xendomains += "/etc/init.d/xendomains"
FILES_${PN}-xen-watchdog += "/etc/init.d/xen-watchdog"

# configure init.d scripts
INITSCRIPT_PACKAGES = "${PN}-xend ${PN}-xencommons ${PN}-xen-watchdog ${PN}-xendomains"
INITSCRIPT_NAME_${PN}-xencommons = "xencommons"
INITSCRIPT_PARAMS_${PN}-xencommons = "defaults 80"
INITSCRIPT_NAME_${PN}-xen-watchdog = "xen-watchdog"
INITSCRIPT_PARAMS_${PN}-xen-watchdog = "defaults 81"
INITSCRIPT_NAME_${PN}-xend = "xend"
INITSCRIPT_PARAMS_${PN}-xend = "defaults 82"
INITSCRIPT_NAME_${PN}-xendomains = "xendomains"
INITSCRIPT_PARAMS_${PN}-xendomains = "defaults 83"

#### REQUIRED ENVIRONMENT VARIABLES ####
export BUILD_SYS
export HOST_SYS
export STAGING_INCDIR
export STAGING_LIBDIR

# specify xen hypervisor to target x86_64 (x86_32 not supported)
export XEN_TARGET_ARCH="x86_64"
export XEN_COMPILE_ARCH="x86_64"

# this is used for the header (#!/usr/bin/python) of the install python scripts
export PYTHONPATH="/usr/bin/python"

# seabios forcefully sets HOSTCC to CC - fixup to allow it to build native conf executable
export HOSTCC="${BUILD_CC}"

# make xen requires CROSS_COMPILE set by hand as it does not abide by ./configure
export CROSS_COMPILE="${TARGET_PREFIX}"

# overide LDFLAGS to allow xen to build without: "x86_64-oe-linux-ld: unrecognized option '-Wl,-O1'"
export LDFLAGS=""

# this is used for the header (#!/usr/bin/python) of the install python scripts
export PYTHONPATH="/usr/bin/python"

do_configure() {
	# fixup qemu-xen-traditional pciutils check hardcoded to test /usr/include/pci
	sed -i 's/\/usr\/include\/pci/$(STAGING_INCDIR)\/pci/g' ${S}/tools/qemu-xen-traditional/xen-hooks.mak

	# fixup for qemu to cross compile
	sed -i 's/configure --d/configure --cross-prefix=${TARGET_PREFIX} --d/g' ${S}/tools/qemu-xen-traditional/xen-setup

	# no stubs-32.h in our 64-bit sysroot - hack it into tools/include/gnu
	test -d ${S}/tools/include/gnu || mkdir ${S}/tools/include/gnu
	if ! test -f ${STAGING_DIR_TARGET}/usr/include/gnu/stubs-32.h ; then
		cat ${STAGING_DIR_TARGET}/usr/include/gnu/stubs-64.h | grep -v stub_bdflush | grep -v stub_getmsg | grep -v stub_putmsg > ${S}/tools/include/gnu/stubs-32.h
		echo \#define __stub___kernel_cosl >> ${S}/tools/include/gnu/stubs-32.h
		echo \#define __stub___kernel_sinl >> ${S}/tools/include/gnu/stubs-32.h
		echo \#define __stub___kernel_tanl >> ${S}/tools/include/gnu/stubs-32.h
	fi

	# do configure
	./configure --exec-prefix=/usr --prefix=/usr --host=${HOST_SYS} --disable-stubdom --disable-ioemu-stubdom --disable-pv-grub --disable-xenstore-stubdom

	# seabios needs a patch to specify correct compiler - pull and patch Makefile
	make -C ${S}/tools/firmware seabios-dir
	sed -i 's/export HOSTCC.*$(CC)/export HOSTCC ?= $(CC)/g' ${S}/tools/firmware/seabios-dir/Makefile
}

do_compile() {
	oe_runmake
}

do_install() {
	oe_runmake DESTDIR="${D}" install

	# install volatiles bits
	install -d ${D}${sysconfdir}/default/volatiles
	echo "d root root 0755 ${localstatedir}/run/xenstored none" \
	     > ${D}${sysconfdir}/default/volatiles/99_xen
	echo "d root root 0755 ${localstatedir}/run/xend none" \
	     >> ${D}${sysconfdir}/default/volatiles/99_xen
	echo "d root root 0755 ${localstatedir}/run/xend/boot none" \
	     >> ${D}${sysconfdir}/default/volatiles/99_xen
	echo "d root root 0755 ${localstatedir}/run/xen none" \
	     >> ${D}${sysconfdir}/default/volatiles/99_xen
	echo "d root root 0755 ${localstatedir}/log/xen none" \
	     >> ${D}${sysconfdir}/default/volatiles/99_xen
	echo "d root root 0755 ${localstatedir}/lock/xen none" \
	     >> ${D}${sysconfdir}/default/volatiles/99_xen
	echo "d root root 0755 ${localstatedir}/lock/subsys none" \
	     >> ${D}${sysconfdir}/default/volatiles/99_xen

	# workaround for xendomains script which searchs sysconfig if directory exists
	install -d ${D}${sysconfdir}/sysconfig
	ln -sf ${sysconfdir}/default/xendomains ${D}${sysconfdir}/sysconfig/xendomains
}

pkg_postinst_${PN}-base() {
	if [ -z "$D" ] && [ -e /etc/init.d/populate-volatile.sh ] ; then
		/etc/init.d/populate-volatile.sh update
	fi
}

sysroot_stage_all_append() {
        sysroot_stage_dir ${D}/boot ${SYSROOT_DESTDIR}/kernel

	install -d ${DEPLOY_DIR_IMAGE}
	install -m 0644 ${D}/boot/xen.gz ${DEPLOY_DIR_IMAGE}/xen-${MACHINE}.gz 
}


