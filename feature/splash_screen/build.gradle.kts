plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

apply<MainGradlePlugin>()

android {
    namespace = ModulePackages.FEATURE_SPLASH_SCREEN

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    navigationComponent()
    lottie()

    // core
    common()
    domain()
}