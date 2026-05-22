plugins {
    id("convention.kotlin.library")
}

dependencies {
    // Common (Result, AppError)
    implementation(project(":core:common"))

    // Coroutines for Flow-based repository interfaces
    implementation(libs.kotlinx.coroutines.android)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(libs.mockk)
}
