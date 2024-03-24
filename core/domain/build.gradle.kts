plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.GMS_SERVICES)
}

apply<MainGradlePlugin>()

android {
    namespace = ModulePackages.CORE_DOMAIN
}

dependencies {
    basic()
    fireBaseAuth()
    dataStore()

    // core
    common()
}