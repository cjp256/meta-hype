FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Enable Xen dom0 support if 'xen' is distro feature.
SRC_URI += "${@base_contains('DISTRO_FEATURES', 'xen', 'file://xen.cfg', '', d)}"

# Enable Xen guest support and related bits for use by an ndvm.
SRC_URI += "${@base_contains('DISTRO_FEATURES', 'xen-ndvm', 'file://xen-ndvm.cfg', '', d)}"

SRCREV_meta_sugarbay = "f706bd410c80a20ff437a53bb3f9f076ba31a17e"
SRCREV_machine_sugarbay = "f20047520a57322f05d95a18a5fbd082fb15cb87"

KERNEL_FEATURES_append += " features/aufs/aufs-enable.scc"
