plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("com.google.gms.google-services")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.core.domain"
}

dependencies {
    basic()
    daggerHilt()
    fireBaseAuth()

    // core
    common()
}

kapt {
    correctErrorTypes = true
}