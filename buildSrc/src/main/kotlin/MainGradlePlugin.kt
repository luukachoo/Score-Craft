import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class MainGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        applyPlugins(project)
        setProjectConfig(project)
    }

    private fun applyPlugins(project: Project) {
        project.apply {
            plugin(Plugins.PLUGIN_ANDROID_LIBRARY)
            plugin(Plugins.PLUGIN_KOTLIN_ANDROID)
            plugin(Plugins.DAGGER_HILT)
            plugin(Plugins.KOTLIN_KAPT)
        }
    }

    private fun setProjectConfig(project: Project) {
        project.android().apply {
            compileSdk = DefaultConfig.COMPILE_SDK

            defaultConfig {
                minSdk = DefaultConfig.MIN_SDK
                testInstrumentationRunner = DefaultConfig.ANDROID_JUNIT_TEST_RUNNER
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_18
                targetCompatibility = JavaVersion.VERSION_18
            }
        }

        project.dependencies {
            daggerHilt()
        }
    }

    private fun Project.android(): LibraryExtension {
        return extensions.getByType(LibraryExtension::class.java)
    }
}