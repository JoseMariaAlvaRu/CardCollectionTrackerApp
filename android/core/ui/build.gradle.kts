plugins {
    id("convention.android.library")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "mx.com.sheff.cardcollectiontracker.core.ui"

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.lifecycle)

    // Debug/preview tooling
    debugImplementation(libs.bundles.compose.debug)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
}
