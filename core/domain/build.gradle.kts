plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.core.domain"
}

dependencies {
    basic()
    daggerHilt()

    // core
    common()
}

kapt {
    correctErrorTypes = true
}