pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MoneyNest"
include(":app:androidApp")
include(":app:iosApp")
include(":app")
include(":konsistTest")