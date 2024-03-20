import basic
import common
import daggerHilt
import domain
import fireBaseAuth
import firebaseDataBase
import firebaseStorage
import retrofit
import workManager

plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.GMS_SERVICES)
}

apply<MainGradlePlugin>()

android {
    namespace = ModulePackages.CORE_DATA
}

dependencies {
    basic()
    retrofit()
    daggerHilt()
    fireBaseAuth()
    firebaseDataBase()
    firebaseStorage()
    firebaseMessaging()
    workManager()

    // core
    domain()
    common()
}