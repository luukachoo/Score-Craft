import ProjectConfig.appId
import ProjectConfig.minSdk
import ProjectConfig.targetSdk
import ProjectConfig.versionCode
import ProjectConfig.versionName

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.marketmingle"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        appId
        minSdk
        targetSdk
        versionCode
        versionName
        "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    basic()
    daggerHilt()
}