pluginManagement {
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
        jcenter()
        maven("https://maven.google.com")
        maven("https://jrepo.sohatv.vn/artifactory/MysohaSDK")
        maven("https://jitpack.io")
    }
}

rootProject.name = "NewsAppGraduation"
include(":app")
 