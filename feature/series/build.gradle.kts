import dependency.basic
import dependency.common
import dependency.coreUi
import dependency.domain
import dependency.navigationComponent
import module.ModulePackages
import plugin.MainGradlePlugin
import plugin.Plugins

plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

apply<MainGradlePlugin>()

android {
    namespace = ModulePackages.FEATURE_SERIES

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    navigationComponent()

    // core
    common()
    domain()
    coreUi()
}