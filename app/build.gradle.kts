import config.BuildType
import config.DefaultConfig
import config.Release
import dependency.basic
import dependency.common
import dependency.coreUi
import dependency.daggerHilt
import dependency.data
import dependency.domain
import dependency.featureForgotPassword
import dependency.featureHome
import dependency.featureImageBottomSheet
import dependency.featureLiveMatchDetails
import dependency.featureLiveMatches
import dependency.featureLogin
import dependency.featureMatches
import dependency.featureProfile
import dependency.featureRegister
import dependency.featureSeries
import dependency.featureSplashScreen
import dependency.featureTournament
import dependency.featureWelcome
import dependency.fireBaseAuth
import dependency.firebaseDataBase
import dependency.navigationComponent
import dependency.retrofit
import module.ModulePackages
import plugin.Plugins

plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.DAGGER_HILT)
    id(Plugins.GMS_SERVICES)
    kotlin(Plugins.KAPT)
}

android {
    namespace = ModulePackages.APP
    compileSdk = DefaultConfig.COMPILE_SDK

    defaultConfig {
        applicationId = ModulePackages.APP
        minSdk = DefaultConfig.MIN_SDK
        targetSdk = DefaultConfig.TARGET_SDK
        versionCode = Release.VERSION_CODE
        versionName = Release.VERSION_NAME
        testInstrumentationRunner = DefaultConfig.ANDROID_JUNIT_TEST_RUNNER
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    buildTypes {
        debug {
            buildConfigField(BuildType.TYPE, BuildType.NAME, BuildType.URL)
        }

        release {
            buildConfigField(BuildType.TYPE, BuildType.NAME, BuildType.URL)
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(BuildType.PROGUARD),
                BuildType.PROGUARD_RULES
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = DefaultConfig.JVM_TARGET
    }
}

dependencies {
    basic()
    daggerHilt()
    navigationComponent()
    retrofit()
    fireBaseAuth()
    firebaseDataBase()

    // core
    common()
    data()
    domain()
    coreUi()

    // feature
    featureWelcome()
    featureRegister()
    featureLogin()
    featureHome()
    featureSeries()
    featureLiveMatches()
    featureLiveMatchDetails()
    featureForgotPassword()
    featureProfile()
    featureImageBottomSheet()
    featureSplashScreen()
    featureSeries()
    featureLiveMatchDetails()
    featureTournament()
    featureMatches()
}

kapt {
    correctErrorTypes = true
}