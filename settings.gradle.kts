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
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {
                username = "mapbox"
                password = "sk.eyJ1Ijoibm1hdGhld3M5MyIsImEiOiJjbDkzZ3R5cHAxczllM3VrNGN6d29sank0In0.4XS_wmkrBiS9GLmrXh2rkQ"
            }
        }

    }
}

rootProject.name = "PicQuestV2"
include(":app")
 