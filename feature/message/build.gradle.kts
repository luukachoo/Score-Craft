plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.example.message"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    common()
    coreUi()
    domain()
    glide()
    fireBaseAuth()
    navigationComponent()
}