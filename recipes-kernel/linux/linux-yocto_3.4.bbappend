FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Enable XEN support in the kernel if the feature is enabled.
SRC_URI += "${@base_contains('DISTRO_FEATURES', 'xen', 'file://xen.cfg', '', d)}"
