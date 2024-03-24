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
            buildConfigField(BuildType.TYPE, BuildType.NAME_PANDA, BuildType.URL_PANDA)
            buildConfigField(
                BuildType.TYPE,
                BuildType.NAME_NOTIFICATION,
                BuildType.URL_NOTIFICATIONS
            )
        }

        release {
            buildConfigField(BuildType.TYPE, BuildType.NAME_PANDA, BuildType.URL_PANDA)
            buildConfigField(
                BuildType.TYPE,
                BuildType.NAME_NOTIFICATION,
                BuildType.URL_NOTIFICATIONS
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
    dataStore()

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
    featureChats()
    friendRequest()
    message()
}

kapt {
    correctErrorTypes = true
}