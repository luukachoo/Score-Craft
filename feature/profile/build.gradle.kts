plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("com.google.gms.google-services")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.example.profile"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    glide()
    fireBaseAuth()
    daggerHilt()
    navigationComponent()

    // core
    common()
    domain()
    coreUi()
}

kapt {
    correctErrorTypes = true
}