pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "CardCollectionTracker"
include(":app")
include(":core:common")
include(":core:ui")
include(":core:domain")
include(":core:network")
include(":core:database")
include(":feature:auth")
include(":feature:collection")
include(":feature:loans")
include(":feature:card-detail")
include(":feature:person-detail")