buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.DAGGER_HILT_AGP)
        classpath(Dependencies.ANDROIDX_SAFE_ARGS_PLUGIN)
        classpath(Dependencies.KOTLIN_GRADLE_PLUGIN)
        classpath(Dependencies.FIREBASE_AGP)
    }
}