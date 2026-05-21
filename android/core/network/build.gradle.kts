plugins {
    id("convention.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "mx.com.sheff.cardcollectiontracker.core.network"
}

dependencies {
    // Depend on domain layer for mapping DTOs to domain models
    implementation(project(":core:domain"))

    // Networking stack
    implementation(libs.bundles.network)

    // Coroutines for suspend support in Retrofit
    implementation(libs.kotlinx.coroutines.android)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
}
