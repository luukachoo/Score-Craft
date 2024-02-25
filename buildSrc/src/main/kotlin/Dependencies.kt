import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val junit = "junit:junit:${Versions.junit}"
    const val androidxTestExtJunit = "androidx.test.ext:junit:${Versions.androidxTestExtJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

    // Retrofit and Moshi with OkHttp
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi${Versions.retrofitVersion}"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshiVersion}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"

    // Dagger - Hilt
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val daggerHiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
    const val daggerHiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomCoroutine = "androidx.room:room-ktx:${Versions.roomVersion}"

    // Navigation Component
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
    const val androidxNavigationSafeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}"

    // Firebase
    const val firebaseAuth = "com.google.firebase:firebase-auth:${Versions.firebaseAuthVersion}"
    const val firebaseDatabase = "com.google.firebase:firebase-database:${Versions.firebaseDatabaseVersion}"

    // WorkManager
    const val workManager = "androidx.work:work-runtime-ktx:${Versions.workManagerVersion}"

    // Coroutine ViewModel Lifecycle Scopes
    const val coroutineLifecycleScope = "androidx.fragment:fragment-ktx:${Versions.coroutineLifecycleScopeVersion}"

    // Splash Screen
    const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashVersion}"
}

fun DependencyHandler.basic() {
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintlayout)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidxTestExtJunit)
    androidTestImplementation(Dependencies.espressoCore)
}

fun DependencyHandler.room() {
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomCoroutine)
    ksp(Dependencies.roomCompiler)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.moshiConverter)
    implementation(Dependencies.moshi)
    implementation(Dependencies.okhttp)
    implementation(Dependencies.okhttpInterceptor)
}

fun DependencyHandler.daggerHilt() {
    implementation(Dependencies.daggerHilt)
    kapt(Dependencies.daggerHiltCompiler)
}

fun DependencyHandler.navigationComponent() {
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUi)
}

// example of how to implement modules
fun DependencyHandler.data() {
    implementation(project(":core:data"))
}

fun DependencyHandler.welcome() {
    implementation(project(":feature:welcome"))
}

fun DependencyHandler.common() {
    implementation(project(":core:common"))
}