package com.kevinvi.mangadisplay.template.plugins

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JavaToolchainService
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.UsesKotlinJavaToolchain

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = PluginSdkVersion.COMPILE_SDK

        defaultConfig {
            minSdk = PluginSdkVersion.MIN_SDK
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17

            isCoreLibraryDesugaringEnabled = true
        }
    }

    val customLauncher = project.extensions.getByType<JavaToolchainService>().launcherFor {
        languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_17.toString().toInt()))
    }
    project.tasks.withType<UsesKotlinJavaToolchain>().configureEach {
        kotlinJavaToolchain.toolchain.use(customLauncher)
    }

    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
    }
}
