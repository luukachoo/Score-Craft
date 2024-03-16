plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.core.data"
}

dependencies {
    basic()
    retrofit()
    daggerHilt()
    fireBaseAuth()
    firebaseDataBase()
    firebaseStorage()
    firebaseMessaging()
    workManager()

    // core
    domain()
    common()
}