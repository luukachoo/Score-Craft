package dependency

import config.ConfigurationNames
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependency: String) {
    add(ConfigurationNames.IMPLEMENTATION, dependency)
}

fun DependencyHandler.implementation(dependency: Dependency) {
    add(ConfigurationNames.IMPLEMENTATION, dependency)
}

fun DependencyHandler.api(dependency: Dependency) {
    add(ConfigurationNames.API, dependency)
}

fun DependencyHandler.test(dependency: String) {
    add(ConfigurationNames.TEST, dependency)
}

fun DependencyHandler.androidTest(dependency: String) {
    add(ConfigurationNames.ANDROID_TEST, dependency)
}

fun DependencyHandler.testImplementation(dependency: String) {
    add(ConfigurationNames.TEST_IMPLEMENTATION, dependency)
}

fun DependencyHandler.androidTestImplementation(dependency: String) {
    add(ConfigurationNames.ANDROID_TEST_IMPLEMENTATION, dependency)
}

fun DependencyHandler.kapt(dependency: String) {
    add(ConfigurationNames.KAPT, dependency)
}

fun DependencyHandler.ksp(dependency: String) {
    add(ConfigurationNames.KSP, dependency)
}