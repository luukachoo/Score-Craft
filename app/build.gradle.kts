import com.android.build.api.dsl.PackagingOptions

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.marketmingle"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_PANDA_SCORE_URL", "\"https://api.pandascore.co/\"")
            buildConfigField(
                "String",
                "BASE_PUSH_NOTIFICATION_URL",
                "\"https://fcm.googleapis.com/fcm/send/\""
            )
        }

        release {
            buildConfigField("String", "BASE_PANDA_SCORE_URL", "\"https://api.pandascore.co/\"")
            buildConfigField(
                "String",
                "BASE_PUSH_NOTIFICATION_URL",
                "\"https://fcm.googleapis.com/fcm/send/\""
            )
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
}

dependencies {
    basic()
    daggerHilt()
    navigationComponent()
    retrofit()
    fireBaseAuth()
    firebaseDataBase()
    firebaseMessaging()

    // core
    common()
    data()
    domain()

    // feature
    featureWelcome()
    featureRegister()
    featureLogin()
    featureHome()
    featureSeries()
    featureLiveMatches()
    featureLiveMatchDetails()
    featureUpcomingMatches()
    featurePastMatches()
    featureForgotPassword()
    featureProfile()
    featureImageBottomSheet()
    featureSplashScreen()
    featureSeries()
    featureLiveMatchDetails()
    featureLiveMatches()
    featureTournament()
    featureChats()
    friendRequest()
}

kapt {
    correctErrorTypes = true
}