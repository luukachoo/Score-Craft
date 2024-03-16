buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.daggerHiltAgp)
        classpath(Dependencies.androidxNavigationSafeArgsGradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
        classpath(Dependencies.firebaseAgp)
        classpath("com.google.gms:google-services:4.4.1")
    }
}