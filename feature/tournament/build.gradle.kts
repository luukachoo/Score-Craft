import dependency.basic
import dependency.common
import dependency.domain
import dependency.glide
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
    namespace = ModulePackages.FEATURE_TOURNAMENT

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    navigationComponent()
    glide()

    // core
    domain()
    common()
}