plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
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
    common()
    daggerHilt()
    navigationComponent()
}

kapt {
    correctErrorTypes = true
}