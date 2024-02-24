plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.core.data"
}

dependencies {
    retrofit()
    daggerHilt()
}