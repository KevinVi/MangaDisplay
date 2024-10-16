import com.android.build.api.dsl.ApplicationExtension
import com.kevinvi.mangadisplay.jetpack.plugins.PluginSdkVersion
import com.kevinvi.mangadisplay.jetpack.plugins.configureAndroidXLifecycle
import com.kevinvi.mangadisplay.jetpack.plugins.configureAndroidXNavigation
import com.kevinvi.mangadisplay.jetpack.plugins.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)

                defaultConfig.targetSdk = PluginSdkVersion.TARGET_SDK

                configureAndroidXLifecycle(this)
                configureAndroidXNavigation(this)
            }
        }
    }
}
