import dependency.basic
import dependency.common
import dependency.coreUi
import dependency.daggerHilt
import dependency.domain
import dependency.fireBaseAuth
import dependency.glide
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
    namespace = ModulePackages.FEATURE_PROFILE

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    glide()
    fireBaseAuth()
    daggerHilt()
    navigationComponent()

    // core
    common()
    domain()
    coreUi()
}