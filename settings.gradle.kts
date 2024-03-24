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
include(":core-ui")
include(":core:domain")
include(":feature:welcome")
include(":feature:registration")
include(":feature:login")
include(":feature:home")
include(":feature:match")
include(":feature:series")
include(":feature:forgot_password")
include(":feature:profile")
include(":feature:image_bottom_sheet")
include(":feature:splash_screen")
include(":feature:live_match_details")
include(":feature:tournament")
include(":feature:chats")
include(":feature:friend_request")
include(":feature:message")
include(":feature:friend_request")
include(":feature:chats")
include(":feature:friend_profile")

