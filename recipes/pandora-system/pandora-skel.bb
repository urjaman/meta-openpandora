DESCRIPTION = "Default 'new user' files on the OpenPandora."

COMPATIBLE_MACHINE = "omap3-pandora"

# /etc/skel is used by Shadow's useradd so you really have that installed for this to make sense ;)
RDEPENDS = "shadow"

PR = "r1"

SRC_URI = " \
  file://.xinitrc \     
  file://bashrc \
  file://profile \
  file://mplayconfig \
  file://pam_environment \
  file://xsettings.xml \
  file://pndXmodmap \
  file://asoundrc \
"

# xsettings.xml is a hack until I can figure out why XDG configs are not setting the icon theme for Xfce/GTK+

do_install() {
  install -d ${D}${sysconfdir}/skel/
  install -m 0644 ${WORKDIR}/.xinitrc ${D}${sysconfdir}/skel/.xinitrc
  install -m 0644 ${WORKDIR}/bashrc ${D}${sysconfdir}/skel/.bashrc
  install -m 0644 ${WORKDIR}/profile ${D}${sysconfdir}/skel/.profile
  install -m 0644 ${WORKDIR}/pam_environment ${D}${sysconfdir}/skel/.pam_environment
  install -m 0644 ${WORKDIR}/pndXmodmap ${D}${sysconfdir}/skel/.pndXmodmap
  install -m 0644 ${WORKDIR}/asoundrc ${D}${sysconfdir}/skel/.asoundrc
  
  install -d ${D}${sysconfdir}/skel/Applications/Settings/xfce4/xfconf/xfce-perchannel-xml
  install -m 0644 ${WORKDIR}/xsettings.xml ${D}${sysconfdir}/skel/Applications/Settings/xfce4/xfconf/xfce-perchannel-xml/xsettings.xml
  
  install -d ${D}${sysconfdir}/skel/.mplayer/
  install -m 0644 ${WORKDIR}/mplayconfig ${D}${sysconfdir}/skel/.mplayer/config
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} += "${prefix} ${datadir}"
