plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
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
    common()
    fireBaseAuth()
    daggerHilt()
    navigationComponent()

    // core
    domain()
}