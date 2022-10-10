SECTION = "kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM ?= "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
SUMMARY = "Allwinner T113 mainline kernel"
COMPATIBLE_MACHINE = "(^mango-t113$)"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

DEPENDS += "coreutils-native"
DEPENDS += "libyaml-native"

# PACKAGECONFIG ??= ""
# PACKAGECONFIG[dt-validation] = ",,python3-dtschema-native"

DEPENDS += "${@bb.utils.contains('PACKAGECONFIG', 'dt-validation', '', 'python3-dtschema-wrapper-native', d)}"

PV = "${LINUX_VERSION}+git${SRCPV}"

SRC_URI = " \
			git://github.com/nquest/linux.git;branch=${KBRANCH};protocol=https;name=machine \
			file://defconfig \
		"

SRC_URI += " \
			file://bridge.cfg \
			file://ipset.cfg \
			file://netfilter.cfg \
		"

KBRANCH = "t113"
SRCREV_machine="b7ba0f20d88299d013cd521699fa47bf8cf5818c"




LINUX_VERSION ?= "6.0.0"
LINUX_VERSION_EXTENSION:append = "-t113"

S = "${WORKDIR}/git"

inherit kernel-yocto kernel pkgconfig
FILES:${KERNEL_PACKAGE_NAME}-base += "${nonarch_base_libdir}/modules/${KERNEL_VERSION}/modules.builtin.modinfo"


KCONFIG_MODE="--alldefconfig"
