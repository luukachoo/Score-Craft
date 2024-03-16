import dependency.basic
import dependency.common
import dependency.daggerHilt
import dependency.fireBaseAuth
import module.ModulePackages
import plugin.MainGradlePlugin
import plugin.Plugins

plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.GMS_SERVICES)
}

apply<MainGradlePlugin>()

android {
    namespace = ModulePackages.CORE_DOMAIN
}

dependencies {
    basic()
    daggerHilt()
    fireBaseAuth()

    // core
    common()
}