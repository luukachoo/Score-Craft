plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

apply<MainGradlePlugin>()

android {
    namespace = ModulePackages.FEATURE_FORGOT_PASSWORD

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    navigationComponent()

    // core
    common()
    domain()
    coreUi()
}