plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.GMS_SERVICES)
}

apply<MainGradlePlugin>()

android {
    namespace = ModulePackages.FEATURE_PROFILE

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    glide()
    fireBaseAuth()
    daggerHilt()
    navigationComponent()

    // core
    common()
    domain()
}