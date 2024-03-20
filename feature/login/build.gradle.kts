import basic
import common
import daggerHilt
import domain
import fireBaseAuth
import gmsPlayServices
import navigationComponent

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