plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
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
    navigationComponent()

    // core
    common()
    domain()
    coreUi()
}