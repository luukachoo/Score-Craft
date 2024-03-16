import dependency.basic
import dependency.common
import dependency.coreUi
import dependency.domain
import dependency.fireBaseAuth
import dependency.firebaseDataBase
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
    namespace = ModulePackages.FEATURE_HOME

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    navigationComponent()
    glide()
    fireBaseAuth()
    firebaseDataBase()

    // core
    domain()
    common()
    coreUi()
}