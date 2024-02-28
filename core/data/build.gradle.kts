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
    firebaseDataBase()

    // core
    common()
    domain()

}