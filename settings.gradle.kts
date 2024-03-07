pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Market Mingle"
include(":app")
include(":core:common")
include(":core:data")
include(":feature:welcome")
include(":feature:registration")
include(":feature:login")
include(":feature:home")
include(":core:domain")
include(":feature:live_matches")
include(":feature:series")
include(":feature:live_matches")
include(":feature:series")
