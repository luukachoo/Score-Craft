plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.core.domain"
}

dependencies {
    basic()
    daggerHilt()
    firebaseAuth()

    // core
    common()
}