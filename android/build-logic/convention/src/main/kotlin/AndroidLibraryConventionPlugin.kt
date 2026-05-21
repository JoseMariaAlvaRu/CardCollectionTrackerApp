import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Convention plugin applied to Android library modules
 * (:core:common, :core:ui, :core:network, :core:database, etc.)
 *
 * Apply with:
 *   plugins {
 *       id("convention.android.library")
 *   }
 *
 * Modules still need to set their own `namespace` and add specific
 * dependencies.
 */
class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jetbrains.kotlin.android")

            extensions.configure<LibraryExtension> {
                configureAndroidCommon(this)
            }
        }
    }
}
