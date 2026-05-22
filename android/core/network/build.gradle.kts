plugins {
    id("convention.android.library")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
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

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
}
