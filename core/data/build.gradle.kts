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
    fireBaseAuth()
    firebaseDataBase()
    firebaseStorage()
    firebaseMessaging()
    workManager()

    // core
    domain()
    common()
}