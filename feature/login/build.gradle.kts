plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("com.google.gms.google-services")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.example.login"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    common()
    domain()
    fireBaseAuth()
    daggerHilt()
    navigationComponent()
}

kapt {
    correctErrorTypes = true
}