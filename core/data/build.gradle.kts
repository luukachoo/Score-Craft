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
    firebaseAuth()
    firebaseDataBase()
    firebaseStorage()
    workManager()

    // core
    domain()
    common()
}