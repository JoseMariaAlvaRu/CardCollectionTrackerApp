plugins {
    `kotlin-dsl`
}

group = "mx.com.sheff.cardcollectiontracker.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "convention.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "convention.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
    }
}
