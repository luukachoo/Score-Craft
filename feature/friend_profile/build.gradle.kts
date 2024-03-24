plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

apply<MainGradlePlugin>()


android {
    namespace = "com.example.friend_profile"

    buildFeatures {
        viewBinding = true
    }
}


dependencies {
    basic()
    glide()
    navigationComponent()

    //data
    common()
    domain()
}