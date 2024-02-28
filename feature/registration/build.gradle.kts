plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.example.registration"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    navigationComponent()

    // core
    common()
}