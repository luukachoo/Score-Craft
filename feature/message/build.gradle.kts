plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

apply<MainGradlePlugin>()

android {
    namespace = ModulePackages.FEATURE_MESSAGE

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