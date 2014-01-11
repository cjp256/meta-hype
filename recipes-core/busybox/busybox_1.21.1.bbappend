FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " file://hype-syslog-startup.conf "

do_install_append () {
        install -m 644 ${WORKDIR}/hype-syslog-startup.conf ${D}${sysconfdir}/syslog-startup.conf
}
