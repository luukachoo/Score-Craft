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
            buildConfigField("String", "BASE_PANDA_SCORE_URL", "\"https://api.pandascore.co/\"")
            buildConfigField(
                "String",
                "BASE_PUSH_NOTIFICATION_URL",
                "\"https://fcm.googleapis.com/fcm/send/\""
            )
        }

        release {
            buildConfigField(BuildType.TYPE, BuildType.NAME, BuildType.URL)
            buildConfigField("String", "BASE_PANDA_SCORE_URL", "\"https://api.pandascore.co/\"")
            buildConfigField(
                "String",
                "BASE_PUSH_NOTIFICATION_URL",
                "\"https://fcm.googleapis.com/fcm/send/\""
            )
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
    featureForgotPassword()
    featureProfile()
    featureImageBottomSheet()
    featureSplashScreen()
    featureSeries()
    featureLiveMatchDetails()
    featureTournament()
    featureChats()
    friendRequest()
}

kapt {
    correctErrorTypes = true
}