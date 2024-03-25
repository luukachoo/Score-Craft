plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

apply<MainGradlePlugin>()

android {
    namespace = ModulePackages.FEATURE_LIVE_MATCH_DETAILS

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    navigationComponent()
    glide()
    lottie()

    // core
    domain()
    common()
    coreUi()
}