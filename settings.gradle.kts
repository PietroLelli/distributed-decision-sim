pluginManagement {
    plugins {
        kotlin("jvm") version "2.1.0"
    }
}
plugins {
    id("com.gradle.enterprise") version "3.18.2"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

rootProject.name = "alchemist-primer"

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishOnFailure()
    }
}
