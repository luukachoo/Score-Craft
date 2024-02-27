plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.example.forgot_password"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    common()
    daggerHilt()
    navigationComponent()
}

kapt {
    correctErrorTypes = true
}