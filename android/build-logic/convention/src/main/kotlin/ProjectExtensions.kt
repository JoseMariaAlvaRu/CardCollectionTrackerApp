import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/**
 * Returns the version catalog 'libs' so convention plugins can resolve
 * versions and dependencies declared in libs.versions.toml.
 *
 * Usage:
 *   val libs = project.libs
 *   val agpVersion = libs.findVersion("agp").get().requiredVersion
 */
internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")
