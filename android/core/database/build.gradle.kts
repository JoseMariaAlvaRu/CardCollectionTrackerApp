plugins {
    id("convention.android.library")
    alias(libs.plugins.ksp)
}

android {
    namespace = "mx.com.sheff.cardcollectiontracker.core.database"
}

dependencies {
    // Depend on domain layer for mapping entities to domain models
    implementation(project(":core:domain"))

    // Room (local DB)
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)

    // Coroutines for Flow-based DAOs and suspend queries
    implementation(libs.kotlinx.coroutines.android)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
}
