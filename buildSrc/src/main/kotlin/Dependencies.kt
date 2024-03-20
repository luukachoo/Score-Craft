import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val CONSTRAINTLAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINTLAYOUT}"
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val ANDROIDX_TEST_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"

    // Retrofit and Moshi with OkHttp
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    const val MOSHI_CONVERTER = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT_VERSION}"
    const val MOSHI = "com.squareup.moshi:moshi-kotlin:${Versions.MOSHI_VERSION}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP_VERSION}"
    const val OKHTTP_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_VERSION}"

    // Dagger - Hilt
    const val DAGGER_HILT = "com.google.dagger:hilt-android:${Versions.HILT_VERSION}"
    const val DAGGER_HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT_VERSION}"
    const val DAGGER_HILT_AGP = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT_VERSION}"

    // Glide
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE_VERSION}"

    // Room
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM_VERSION}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM_VERSION}"
    const val ROOM_COROUTINE = "androidx.room:room-ktx:${Versions.ROOM_VERSION}"

    // Navigation Component
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION_VERSION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION_VERSION}"
    const val ANDROIDX_SAFE_ARGS_PLUGIN = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION_VERSION}"

    // Firebase
    const val FIREBASE_AGP = "com.google.gms:google-services:${Versions.GMS_VERSION}"
    const val FIREBASE_AUTH = "com.google.firebase:firebase-auth:${Versions.FIREBASE_AUTH_VERSION}"
    const val GMS_PLAY_SERVICES = "com.google.android.gms:play-services-auth:${Versions.GMS_PLAY_SERVICES_VERSION}"
    const val FIREBASE_STORAGE = "com.google.firebase:firebase-storage-ktx:${Versions.FIREBASE_DATABASE_VERSION}"
    const val FIREBASE_DATABASE = "com.google.firebase:firebase-database:${Versions.FIREBASE_DATABASE_VERSION}"

    // WorkManager
    const val WORK_MANAGER = "androidx.work:work-runtime-ktx:${Versions.WORK_MANAGER_VERSION}"

    // Lottie
    const val LOTTIE = "com.airbnb.android:lottie:${Versions.LOTTIE_VERSION}"

    // Kotlin Gradle
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"

}

fun DependencyHandler.basic() {
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINTLAYOUT)
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.ANDROIDX_TEST_JUNIT)
    androidTestImplementation(Dependencies.ESPRESSO_CORE)
}

fun DependencyHandler.workManager() {
    implementation(Dependencies.WORK_MANAGER)
}

fun DependencyHandler.firebaseDataBase() {
    implementation(Dependencies.FIREBASE_DATABASE)
}

fun DependencyHandler.fireBaseAuth() {
    implementation(Dependencies.FIREBASE_AUTH)
}

fun DependencyHandler.gmsPlayServices() {
    implementation(Dependencies.GMS_PLAY_SERVICES)
}

fun DependencyHandler.firebaseStorage() {
    implementation(Dependencies.FIREBASE_STORAGE)
}

fun DependencyHandler.room() {
    implementation(Dependencies.ROOM_RUNTIME)
    implementation(Dependencies.ROOM_COROUTINE)
    ksp(Dependencies.ROOM_COMPILER)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.MOSHI_CONVERTER)
    implementation(Dependencies.MOSHI)
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_INTERCEPTOR)
}

fun DependencyHandler.daggerHilt() {
    implementation(Dependencies.DAGGER_HILT)
    kapt(Dependencies.DAGGER_HILT_COMPILER)
}

fun DependencyHandler.navigationComponent() {
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
}

fun DependencyHandler.lottie() {
    implementation(Dependencies.LOTTIE)
}

fun DependencyHandler.glide() {
    implementation(Dependencies.GLIDE)
}

// example of how to implement modules
fun DependencyHandler.featureWelcome() {
    implementation(project(Modules.FEATURE_WELCOME))
}

fun DependencyHandler.featureLogin() {
    implementation(project(Modules.FEATURE_LOGIN))
}

fun DependencyHandler.featureRegister() {
    implementation(project(Modules.FEATURE_REGISTER))
}

fun DependencyHandler.featureHome() {
    implementation(project(Modules.FEATURE_HOME))
}

fun DependencyHandler.featureForgotPassword() {
    implementation(project(Modules.FEATURE_FORGOT_PASSWORD))
}

fun DependencyHandler.featureProfile() {
    implementation(project(Modules.FEATURE_PROFILE))
}

fun DependencyHandler.featureImageBottomSheet() {
    implementation(project(Modules.FEATURE_BOTTOM_SHEET))
}

fun DependencyHandler.featureSplashScreen() {
    implementation(project(Modules.FEATURE_SPLASH))
}

fun DependencyHandler.featureLiveMatches() {
    implementation(project(Modules.FEATURE_MATCH))
}

fun DependencyHandler.featureSeries() {
    implementation(project(Modules.FEATURE_SERIES))
}

fun DependencyHandler.featureLiveMatchDetails() {
    implementation(project(Modules.FEATURE_LIVE_MATCH_DETAILS))
}

fun DependencyHandler.featureTournament() {
    implementation(project(Modules.FEATURE_TOURNAMENT))
}

fun DependencyHandler.featureTournamentDetails() {
    implementation(project(Modules.FEATURE_TOURNAMENT_DETAILS))
}

fun DependencyHandler.data() {
    implementation(project(Modules.CORE_DATA))
}

fun DependencyHandler.common() {
    implementation(project(Modules.CORE_COMMON))
}

fun DependencyHandler.domain() {
    implementation(project(Modules.CORE_DOMAIN))
}