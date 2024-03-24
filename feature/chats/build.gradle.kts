plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

apply<MainGradlePlugin>()

android {
    namespace = "com.example.chats"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    glide()
    navigationComponent()

    // core
    common()
    coreUi()
    domain()
}