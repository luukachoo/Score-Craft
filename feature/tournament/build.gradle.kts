plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.SAFE_ARGS)
}

apply<MainGradlePlugin>()

android {
    namespace = ModulePackages.FEATURE_TOURNAMENT

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
    coreUi()
}