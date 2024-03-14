plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
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
    fireBaseAuth()
    daggerHilt()
    navigationComponent()

    // core
    common()
    domain()
    coreUi()
}