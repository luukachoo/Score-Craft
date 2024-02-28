plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
}