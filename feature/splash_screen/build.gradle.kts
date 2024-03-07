plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.example.splash_screen"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    daggerHilt()
    navigationComponent()

    // core
    common()
    domain()
}