plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.core.common"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    daggerHilt()
    fireBaseAuth()
    firebaseDataBase()
    retrofit()

//    data()
}