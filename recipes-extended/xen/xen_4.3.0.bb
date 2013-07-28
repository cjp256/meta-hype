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

COMPATIBLE_HOST = '(x86_64.*|i.86.*).*-linux'

inherit autotools gettext setuptools update-rc.d

DEPENDS = "util-linux util-linux-native file-native zlib ncurses openssl bison-native flex-native gettext dev86-native iasl-native pciutils virtual/libgl virtual/libsdl bridge-utils iproute2 procps yajl pixman python xz-native"

RDEPENDS_xen-base = "\
	libgcc pciutils bridge-utils iproute2 util-linux udev procps bash coreutils python python-core python-shell python-pprint perl xz \
	${PN}-doc \
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
	${PN}-fsimage \
	${PN}-keymaps-base \
	${PN}-keymaps \
	${PN}-python \
	${PN}-bios-ppc \
	${PN}-bios-sparc \
 	${PN}-hvmloader \
	${PN}-palcode-clipper \
	${PN}-xen-watchdog \
	${PN}-xencommons \
	${PN}-xendomains \
	"

PACKAGES = "\
	${PN}-base \
	${PN}-dbg \
	${PN}-doc \
	${PN}-dev \
	${PN}-examples \
	${PN}-staticdev \
	${PN}-libblktapctl ${PN}-libblktapctl-dev \
	${PN}-libxenguest ${PN}-libxenguest-dev \
	${PN}-libxenlight ${PN}-libxenlight-dev \
	${PN}-libxenvchan ${PN}-libxenvchan-dev \
	${PN}-libxenctrl ${PN}-libxenctrl-dev \
	${PN}-libxlutil ${PN}-libxlutil-dev \
	${PN}-libvhd ${PN}-libvhd-dev \
	${PN}-libxenstat ${PN}-libxenstat-dev \
	${PN}-libxenstore ${PN}-libxenstore-dev \
	${PN}-libblktap ${PN}-libblktap-dev \
	${PN}-libfsimage ${PN}-libfsimage-dev \
	${PN}-fsimage \
	${PN}-keymaps-base \
	${PN}-keymaps \
	${PN}-python \
	${PN}-bios-ppc \
	${PN}-bios-sparc \
 	${PN}-hvmloader \
	${PN}-palcode-clipper \
	${PN}-xend \
	${PN}-xen-watchdog \
	${PN}-xencommons \
	${PN}-xendomains \
	${PN}-volatiles \
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

INSANE_SKIP_${PN}-bios-sparc = "arch"
FILES_${PN}-bios-sparc = "\
	/usr/share/xen/qemu/openbios-sparc64 \
	/usr/share/xen/qemu/openbios-sparc32 \
	/usr/share/qemu-xen/qemu/openbios-sparc64 \
	/usr/share/qemu-xen/qemu/openbios-sparc32 \
	"

INSANE_SKIP_${PN}-bios-ppc = "arch"
FILES_${PN}-bios-ppc = "\
	/usr/share/xen/qemu/openbios-ppc \
	/usr/share/qemu-xen/qemu/openbios-ppc \
	/usr/share/xen/qemu/ppc_rom.bin \
	/usr/share/qemu-xen/qemu/openbios-pp \
	"

INSANE_SKIP_${PN}-palcode-clipper = "arch"
FILES_${PN}-palcode-clipper = "\
	/usr/share/qemu-xen/qemu/palcode-clipper \
	"

INSANE_SKIP_${PN}-hvmloader = "arch"
FILES_${PN}-hvmloader = "\
	/usr/lib/xen/boot/hvmloader \
	"

FILES_${PN}-doc = "\
	/usr/share/doc \
	/usr/share/man \
	"

FILES_${PN}-python = "\
	/usr/lib/python2.7 \
	"

FILES_${PN}-dev = "\
	/usr/include \
	"

FILES_${PN}-keymaps-base = "\
	/usr/share/xen/qemu/keymaps/common \
	/usr/share/xen/qemu/keymaps/modifiers \
	/usr/share/xen/qemu/keymaps/en-us \
	/usr/share/qemu-xen/qemu/keymaps/common \
	/usr/share/qemu-xen/qemu/keymaps/modifiers \
	/usr/share/qemu-xen/qemu/keymaps/en-us \
	"

FILES_${PN}-keymaps = "\
	/usr/share/xen/qemu/keymaps \
	/usr/share/qemu-xen/qemu/keymaps \
	"

FILES_${PN}-volatiles = "\
	/var/log \
	/var/run \
	/var/lock \
	/var/volatile \
	"

FILES_${PN}-base = "\
	/boot \
	/var/xen \
	/var/xen/dump \
	/var/lib/xenstored \
	/var/lib/xen \
	/var/lib/xen/xenpaging \
	/usr/lib/xen/bin/qemu-img \
	/usr/lib/xen/bin/qemu-nbd \
	/usr/lib/xen/bin/qemu-ga \
	/usr/lib/xen/bin/xenpvnetboot \
	/usr/lib/xen/bin/pygrub \
	/usr/lib/xen/bin/lsevtchn \
	/usr/lib/xen/bin/qemu-io \
	/usr/lib/xen/bin/xenctx \
	/usr/lib/xen/bin/xc_restore \
	/usr/lib/xen/bin/qemu-system-i386 \
	/usr/lib/xen/bin/qemu-dm \
	/usr/lib/xen/bin/xc_save \
	/usr/lib/xen/bin/virtfs-proxy-helper \
	/usr/lib/xen/bin/xenconsole \
	/usr/lib/xen/bin/libxl-save-helper \
	/usr/lib/xen/bin/readnotes \
	/usr/lib/xen/bin/xenpaging \
	/usr/sbin/tap-ctl \
	/usr/sbin/xencov \
	/usr/sbin/flask-set-bool \
	/usr/sbin/vhd-util \
	/usr/sbin/xen-tmem-list-parse \
	/usr/sbin/xenstored \
	/usr/sbin/xm \
	/usr/sbin/flask-label-pci \
	/usr/sbin/xenmon.py \
	/usr/sbin/xen-hptool \
	/usr/sbin/xsview \
	/usr/sbin/xend \
	/usr/sbin/blktapctrl \
	/usr/sbin/tapdisk-stream \
	/usr/sbin/xen-ringwatch \
	/usr/sbin/xenpm \
	/usr/sbin/kdd \
	/usr/sbin/xen-bugtool \
	/usr/sbin/lock-util \
	/usr/sbin/xenbaked \
	/usr/sbin/xen-hvmcrash \
	/usr/sbin/vhd-update \
	/usr/sbin/qcow2raw \
	/usr/sbin/xl \
	/usr/sbin/tapdisk2 \
	/usr/sbin/tapdisk-diff \
	/usr/sbin/xentrace_setmask \
	/usr/sbin/tapdisk \
	/usr/sbin/xenwatchdogd \
	/usr/sbin/xenlockprof \
	/usr/sbin/gtraceview \
	/usr/sbin/td-util \
	/usr/sbin/tapdisk-client \
	/usr/sbin/qcow-create \
	/usr/sbin/xen-hvmctx \
	/usr/sbin/xenpmd \
	/usr/sbin/xen-python-path \
	/usr/sbin/xentop \
	/usr/sbin/flask-setenforce \
	/usr/sbin/xenperf \
	/usr/sbin/img2qcow \
	/usr/sbin/gdbsx \
	/usr/sbin/flask-loadpolicy \
	/usr/sbin/gtracestat \
	/usr/sbin/flask-get-bool \
	/usr/sbin/xenconsoled \
	/usr/sbin/flask-getenforce \
	/usr/sbin/xen-lowmemd \
	/usr/libexec/qemu-bridge-helper \
	/usr/etc/qemu/target-x86_64.conf \
	/usr/share/xen/create.dtd \
	/usr/share/xen/qemu/bamboo.dtb \
	/usr/share/xen/qemu/pxe-pcnet.bin \
	/usr/share/xen/qemu/pxe-ne2k_pci.bin \
	/usr/share/xen/qemu/video.x \
	/usr/share/xen/qemu/vgabios-cirrus.bin \
	/usr/share/xen/qemu/pxe-rtl8139.bin \
	/usr/share/xen/qemu/bios.bin \
	/usr/share/xen/qemu/pxe-e1000.bin \
	/usr/share/xen/qemu/ppc_rom.bin \
	/usr/share/xen/qemu/vgabios.bin \
	/usr/share/qemu-xen/qemu/bamboo.dtb \
	/usr/share/qemu-xen/qemu/pxe-pcnet.rom \
	/usr/share/qemu-xen/qemu/vgabios-vmware.bin \
	/usr/share/qemu-xen/qemu/pxe-eepro100.rom \
	/usr/share/qemu-xen/qemu/pxe-e1000.rom \
	/usr/share/qemu-xen/qemu/multiboot.bin \
	/usr/share/qemu-xen/qemu/vgabios-cirrus.bin \
	/usr/share/qemu-xen/qemu/bios.bin \
	/usr/share/qemu-xen/qemu/vgabios-stdvga.bin \
	/usr/share/qemu-xen/qemu/pxe-ne2k_pci.rom \
	/usr/share/qemu-xen/qemu/spapr-rtas.bin \
	/usr/share/qemu-xen/qemu/slof.bin \
	/usr/share/qemu-xen/qemu/vgabios-qxl.bin \
	/usr/share/qemu-xen/qemu/pxe-rtl8139.rom \
	/usr/share/qemu-xen/qemu/pxe-virtio.rom \
	/usr/share/qemu-xen/qemu/kvmvapic.bin \
	/usr/share/qemu-xen/qemu/petalogix-s3adsp1800.dtb \
	/usr/share/qemu-xen/qemu/sgabios.bin \
	/usr/share/qemu-xen/qemu/linuxboot.bin \
	/usr/share/qemu-xen/qemu/qemu-icon.bmp \
	/usr/share/qemu-xen/qemu/ppc_rom.bin \
	/usr/share/qemu-xen/qemu/vgabios.bin \
	/usr/share/qemu-xen/qemu/s390-zipl.rom \
	/usr/share/qemu-xen/qemu/petalogix-ml605.dtb \
	/usr/bin/remus \
	/usr/bin/qemu-nbd-xen \
	/usr/bin/xenstore-chmod \
	/usr/bin/xencov_split \
	/usr/bin/qemu-img-xen \
	/usr/bin/pygrub \
	/usr/bin/xenstore-rm \
	/usr/bin/xen-detect \
	/usr/bin/xentrace_format \
	/usr/bin/xenstore-write \
	/usr/bin/xenstore \
	/usr/bin/xenstore-watch \
	/usr/bin/xentrace_setsize \
	/usr/bin/xenstore-read \
	/usr/bin/xenstore-control \
	/usr/bin/xentrace \
	/usr/bin/xenstore-exists \
	/usr/bin/xenstore-list \
	/usr/bin/xenstore-ls \
	/usr/bin/xencons \
	/etc/udev/rules.d/xen-backend.rules \
	/etc/udev/rules.d/xend.rules \
	/etc/bash_completion.d/xl.sh \
	/etc/default \
	/etc/default/xencommons \
	/etc/default/xendomains \
	/etc/xen \
	/etc/xen/xend-pci-permissive.sxp \
	/etc/xen/xmexample1 \
	/etc/xen/xmexample.nbd \
	/etc/xen/xmexample3 \
	/etc/xen/xlexample.hvm \
	/etc/xen/xmexample.hvm-stubdom \
	/etc/xen/auto \
	/etc/xen/xmexample2 \
	/etc/xen/README.incompatibilities \
	/etc/xen/xend-pci-quirks.sxp \
	/etc/xen/xlexample.pvlinux \
	/etc/xen/xl.conf \
	/etc/xen/xmexample.vti \
	/etc/xen/README \
	/etc/xen/xmexample.pv-grub \
	/etc/xen/cpupool \
	/etc/xen/scripts \
	/etc/xen/scripts/hotplugpath.sh \
	/etc/xen/scripts/network-route \
	/etc/xen/scripts/block-iscsi \
	/etc/xen/scripts/xen-hotplug-common.sh \
	/etc/xen/scripts/block \
	/etc/xen/scripts/external-device-migrate \
	/etc/xen/scripts/xen-network-common.sh \
	/etc/xen/scripts/vif-openvswitch \
	/etc/xen/scripts/block-nbd \
	/etc/xen/scripts/vif-bridge \
	/etc/xen/scripts/network-bridge \
	/etc/xen/scripts/vif-common.sh \
	/etc/xen/scripts/qemu-ifup \
	/etc/xen/scripts/network-nat \
	/etc/xen/scripts/xen-hotplug-cleanup \
	/etc/xen/scripts/vscsi \
	/etc/xen/scripts/block-enbd \
	/etc/xen/scripts/block-common.sh \
	/etc/xen/scripts/vif-nat \
	/etc/xen/scripts/vif-setup \
	/etc/xen/scripts/blktap \
	/etc/xen/scripts/locking.sh \
	/etc/xen/scripts/vif-route \
	/etc/xen/scripts/logging.sh \
	/etc/xen/scripts/vif2 \
	/etc/xen/scripts/xen-script-common.sh \
	/etc/xen/xm-config.xml \
	/etc/xen/xend-config.sxp \
	/etc/xen/xmexample.hvm \
	"

FILES_${PN}-xencommons += "/etc/init.d/xencommons"
FILES_${PN}-xend += "/etc/init.d/xend"
FILES_${PN}-xendomains += "/etc/init.d/xendomains"
FILES_${PN}-xen-watchdog += "/etc/init.d/xen-watchdog"

# Configure init.d scripts... 80 sounds like a good starting number.
INITSCRIPT_PACKAGES = "${PN}-xend ${PN}-xencommons ${PN}-xen-watchdog ${PN}-xendomains"

INITSCRIPT_NAME_${PN}-xencommons = "xencommons"
INITSCRIPT_PARAMS_${PN}-xencommons = "defaults 80"

INITSCRIPT_NAME_${PN}-xen-watchdog = "xen-watchdog"
INITSCRIPT_PARAMS_${PN}-xen-watchdog = "defaults 81"

INITSCRIPT_NAME_${PN}-xend = "xend"
INITSCRIPT_PARAMS_${PN}-xend = "defaults 82"

INITSCRIPT_NAME_${PN}-xendomains = "xendomains"
INITSCRIPT_PARAMS_${PN}-xendomains = "defaults 83"

do_configure_prepend() {
	export BUILD_SYS=${BUILD_SYS}
	export HOST_SYS=${HOST_SYS}
	export STAGING_INCDIR=${STAGING_INCDIR}
	export STAGING_LIBDIR=${STAGING_LIBDIR}

	# XXX: legit? better way to do this? 
	export XEN_TARGET_ARCH=`echo ${TARGET_ARCH} | sed -e s/i.86/x86_32/ \
                                -e s/i86pc/x86_32/ -e s/amd64/x86_64/`
	export HOST_STRING="${XEN_TARGET_ARCH}-oe-gnu"

	# make xen requires CROSS_COMPILE set by hand as it does not abide by ./configure
	export CROSS_COMPILE=${TARGET_PREFIX}

	# this is used for the header (#!/usr/bin/python) of the install python scripts
	export PYTHONPATH="/usr/bin/python"
}

do_configure() {
	./configure --exec-prefix=/usr --prefix=/usr --host=${HOST_STRING} --disable-seabios --disable-stubdom --disable-ioemu-stubdom --disable-pv-grub --disable-xenstore-stubdom
}

do_compile_prepend() {
	export BUILD_SYS=${BUILD_SYS}
	export HOST_SYS=${HOST_SYS}
	export STAGING_INCDIR=${STAGING_INCDIR}
	export STAGING_LIBDIR=${STAGING_LIBDIR}

	# make xen requires CROSS_COMPILE set by hand as it does not abide by ./configure
	export CROSS_COMPILE=${TARGET_PREFIX}
	export LDFLAGS=""

	test -d ${S}/tools/firmware/rombios/gnu || mkdir ${S}/tools/firmware/rombios/gnu
	test -e ${S}/tools/firmware/rombios/32bit/gnu || ln -s ../gnu ${S}/tools/firmware/rombios/32bit/gnu
	test -e ${S}/tools/firmware/hvmloader/gnu || ln -s ../rombios/gnu ${S}/tools/firmware/hvmloader/gnu
	test -e ${S}/tools/firmware/hvmloader/acpi/gnu || ln -s ../../rombios/gnu ${S}/tools/firmware/hvmloader/acpi/gnu
	test -d ${S}/tools/include || mkdir -p ${S}/tools/include
	test -e ${S}/tools/include/gnu || ln -s ../firmware/rombios/gnu ${S}/tools/include/gnu

	# No stubs-32 in our 64-bit sysroot - make it
	if ! test -f ${STAGING_DIR_TARGET}/usr/include/gnu/stubs-32.h ; then
		cat ${STAGING_DIR_TARGET}/usr/include/gnu/stubs-64.h | grep -v stub_bdflush | grep -v stub_getmsg | grep -v stub_putmsg > ${S}/tools/firmware/rombios/gnu/stubs-32.h
		echo \#define __stub___kernel_cosl >> ${S}/tools/firmware/rombios/gnu/stubs-32.h
		echo \#define __stub___kernel_sinl >> ${S}/tools/firmware/rombios/gnu/stubs-32.h
		echo \#define __stub___kernel_tanl >> ${S}/tools/firmware/rombios/gnu/stubs-32.h
	fi

	# this is used for the header (#!/usr/bin/python) of the install python scripts
	export PYTHONPATH="/usr/bin/python"
}

do_compile() {
	oe_runmake
}

do_install_prepend() {
	export BUILD_SYS=${BUILD_SYS}
	export HOST_SYS=${HOST_SYS}
	export STAGING_INCDIR=${STAGING_INCDIR}
	export STAGING_LIBDIR=${STAGING_LIBDIR}

	# fact of the matter is that the xen makefile will rebuild stuffs when doing a make install
	# so everything needs to be redefined

	# make xen requires CROSS_COMPILE set by hand as it does not abide by ./configure
	export CROSS_COMPILE=${TARGET_PREFIX}
	export LDFLAGS=""

	test -d ${S}/tools/firmware/rombios/gnu || mkdir ${S}/tools/firmware/rombios/gnu
	test -e ${S}/tools/firmware/rombios/32bit/gnu || ln -s ../gnu ${S}/tools/firmware/rombios/32bit/gnu
	test -e ${S}/tools/firmware/hvmloader/gnu || ln -s ../rombios/gnu ${S}/tools/firmware/hvmloader/gnu
	test -e ${S}/tools/firmware/hvmloader/acpi/gnu || ln -s ../../rombios/gnu ${S}/tools/firmware/hvmloader/acpi/gnu
	test -d ${S}/tools/include || mkdir -p ${S}/tools/include
	test -e ${S}/tools/include/gnu || ln -s ../firmware/rombios/gnu ${S}/tools/include/gnu

	# No stubs-32 in our 64-bit sysroot - make it
	if ! test -f ${STAGING_DIR_TARGET}/usr/include/gnu/stubs-32.h ; then
		cat ${STAGING_DIR_TARGET}/usr/include/gnu/stubs-64.h | grep -v stub_bdflush | grep -v stub_getmsg | grep -v stub_putmsg > ${S}/tools/firmware/rombios/gnu/stubs-32.h
		echo \#define __stub___kernel_cosl >> ${S}/tools/firmware/rombios/gnu/stubs-32.h
		echo \#define __stub___kernel_sinl >> ${S}/tools/firmware/rombios/gnu/stubs-32.h
		echo \#define __stub___kernel_tanl >> ${S}/tools/firmware/rombios/gnu/stubs-32.h
	fi

	# this is used for the header (#!/usr/bin/python) of the install python scripts
	export PYTHONPATH="/usr/bin/python"
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


