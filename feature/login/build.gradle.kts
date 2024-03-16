import dependency.basic
import dependency.common
import dependency.daggerHilt
import dependency.domain
import dependency.fireBaseAuth
import dependency.gmsPlayServices
import dependency.navigationComponent
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
    namespace = ModulePackages.FEATURE_LOGIN

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    fireBaseAuth()
    daggerHilt()
    navigationComponent()
    gmsPlayServices()

    // core
    common()
    domain()
}