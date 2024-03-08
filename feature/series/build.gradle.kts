plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.feature.series"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    navigationComponent()

    // core
    common()
    domain()
}