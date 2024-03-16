import dependency.basic
import dependency.fireBaseAuth
import dependency.firebaseDataBase
import dependency.firebaseStorage
import dependency.glide
import dependency.navigationComponent
import dependency.retrofit
import dependency.workManager
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
    namespace = ModulePackages.CORE_COMMON

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    retrofit()
    glide()
    fireBaseAuth()
    firebaseDataBase()
    firebaseStorage()
    workManager()
    navigationComponent()
}