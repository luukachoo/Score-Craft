plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
    fireBaseAuth()
    daggerHilt()
    navigationComponent()
    implementation("com.google.android.gms:play-services-auth:20.2.0")

    // core
    coreUi()
    common()
    domain()
}