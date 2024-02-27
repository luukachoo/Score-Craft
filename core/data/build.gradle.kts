plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    kotlin("kapt")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.core.data"
}

dependencies {
    basic()
    common()
    retrofit()
    daggerHilt()
    firebaseDataBase()

    // core
    common()
    domain()

}

kapt {
    correctErrorTypes = true
}