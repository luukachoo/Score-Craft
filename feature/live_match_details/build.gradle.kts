plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.feature.live_match_details"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    navigationComponent()
    glide()

    // core
    domain()
    common()
}