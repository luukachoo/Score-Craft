import basic
import fireBaseAuth
import firebaseDataBase
import firebaseStorage
import glide
import navigationComponent
import retrofit
import workManager

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