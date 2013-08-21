FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "${@base_contains('DISTRO_FEATURES', 'xen-ndvm', 'file://xen-ndvm.cfg', '', d)}"
