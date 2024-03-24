plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
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
    glide()
    lottie()
    navigationComponent()

    //data
    common()
    domain()
}