import dependency.basic
import dependency.common
import dependency.daggerHilt
import dependency.domain
import dependency.fireBaseAuth
import dependency.firebaseDataBase
import dependency.firebaseStorage
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
    namespace = ModulePackages.CORE_DATA
}

dependencies {
    basic()
    retrofit()
    daggerHilt()
    fireBaseAuth()
    firebaseDataBase()
    firebaseStorage()
    workManager()

    // core
    domain()
    common()
}