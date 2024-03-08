plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.feature.welcome"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    daggerHilt()
    navigationComponent()
    lottie()

    // core
    common()
}