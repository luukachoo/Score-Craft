buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.daggerHiltAgp)
        classpath(Dependencies.androidxNavigationSafeArgsGradlePlugin)
    }
}