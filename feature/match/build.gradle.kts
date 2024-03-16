import dependency.basic
import dependency.common
import dependency.featureLiveMatches
import dependency.featurePastMatches
import dependency.featureUpcomingMatches
import dependency.navigationComponent
import plugin.MainGradlePlugin

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.feature.match"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    basic()
    navigationComponent()

    // feature
    featureLiveMatches()
    featureUpcomingMatches()
    featurePastMatches()

    // core
    common()
}