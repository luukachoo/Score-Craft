plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
    retrofit()
    glide()
    fireBaseAuth()
    firebaseDataBase()
    firebaseStorage()
    firebaseMessaging()
    workManager()
    navigationComponent()
}