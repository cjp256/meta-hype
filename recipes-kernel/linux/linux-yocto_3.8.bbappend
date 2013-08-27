FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Enable Xen dom0 support if 'xen' is distro feature.
SRC_URI += "${@base_contains('DISTRO_FEATURES', 'xen', 'file://xen.cfg', '', d)}"

# Enable Xen guest support and related bits for use by an ndvm.
SRC_URI += "${@base_contains('DISTRO_FEATURES', 'xen-ndvm', 'file://xen-ndvm.cfg', '', d)}"

KERNEL_FEATURES_append += " features/aufs/aufs-enable.scc"
