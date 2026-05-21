import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

/**
 * Convention plugin applied to feature modules (:feature:*).
 *
 * Apply with:
 *   plugins {
 *       id("convention.android.feature")
 *   }
 *
 * Bundles everything a feature module needs:
 *  - Android library + Kotlin + Compose + Hilt + KSP plugins
 *  - Common Android configuration (compileSdk, JDK 17, etc.)
 *  - Compose enabled
 *  - Dependencies on :core:common, :core:ui, :core:domain
 *  - Compose BOM and bundles
 *  - Navigation Compose + Hilt Navigation Compose
 *  - Hilt runtime + KSP processor
 *  - Coroutines
 *  - Standard testing stack
 *
 * The module only needs to declare its namespace and feature-specific
 * dependencies (if any).
 */
class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            // Apply required plugins
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jetbrains.kotlin.android")
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")
            pluginManager.apply("com.google.dagger.hilt.android")
            pluginManager.apply("com.google.devtools.ksp")

            // Configure android { ... }
            extensions.configure<LibraryExtension> {
                configureAndroidCommon(this)
                buildFeatures {
                    compose = true
                }
            }

            // Wire up dependencies common to all features
            dependencies {
                add("implementation", project(":core:common"))
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:domain"))

                // Compose (BOM-managed)
                add("implementation", platform(libs.findLibrary("androidx-compose-bom").get()))
                add("implementation", libs.findBundle("compose").get())
                add("implementation", libs.findBundle("lifecycle").get())

                // Navigation
                add("implementation", libs.findLibrary("androidx-navigation-compose").get())
                add("implementation", libs.findLibrary("androidx-hilt-navigation-compose").get())

                // Hilt
                add("implementation", libs.findLibrary("hilt-android").get())
                add("ksp", libs.findLibrary("hilt-compiler").get())

                // Coroutines
                add("implementation", libs.findLibrary("kotlinx-coroutines-android").get())

                // Compose debug tooling
                add("debugImplementation", libs.findBundle("compose-debug").get())

                // Testing
                add("testImplementation", libs.findLibrary("junit").get())
                add("testImplementation", libs.findLibrary("mockk").get())
                add("testImplementation", libs.findLibrary("turbine").get())
                add("testImplementation", libs.findLibrary("kotlinx-coroutines-test").get())
                add("androidTestImplementation", libs.findLibrary("androidx-junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx-espresso-core").get())
                add("androidTestImplementation", platform(libs.findLibrary("androidx-compose-bom").get()))
                add("androidTestImplementation", libs.findLibrary("androidx-ui-test-junit4").get())
            }
        }
    }
}
