plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.example.friend_request"

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