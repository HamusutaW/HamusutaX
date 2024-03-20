pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android(?:\\..+)?")
                includeGroupByRegex("com\\.google\\..+")
                includeGroupByRegex("androidx\\..+")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://jitpack.io")
            content {
                includeGroupByRegex("com\\.github\\..+")
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
            content {
                includeGroupByRegex("com\\.github\\..+")
            }
        }
        maven {
            url = uri("https://androidx.dev/storage/compose-compiler/repository/")
            content {
                includeModule("androidx.compose.compiler", "compiler")
            }
        }
    }
}

rootProject.name = "HamusutaX"
include(":core")
include(":android")
include(":okhttp")
include(":hash-extended")
include(":compose")
include(":jsoup")
