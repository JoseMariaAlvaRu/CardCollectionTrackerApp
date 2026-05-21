import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Convention plugin applied to the Android application module (:app).
 *
 * Apply with:
 *   plugins {
 *       id("convention.android.application")
 *   }
 *
 * Configures the same baseline as Android library modules (compileSdk,
 * JDK 17, Kotlin JVM 17) plus app-specific defaults (targetSdk).
 *
 * The module still needs to set: namespace, applicationId, versionCode,
 * versionName, and any app-specific dependencies.
 */
class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            pluginManager.apply("org.jetbrains.kotlin.android")

            extensions.configure<ApplicationExtension> {
                configureAndroidCommon(this)
                defaultConfig {
                    targetSdk = 35
                }
            }
        }
    }
}
