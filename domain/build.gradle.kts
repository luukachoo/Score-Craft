plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.example.domain"
}

dependencies {
    basic()
    daggerHilt()
}

kapt {
    correctErrorTypes = true
}