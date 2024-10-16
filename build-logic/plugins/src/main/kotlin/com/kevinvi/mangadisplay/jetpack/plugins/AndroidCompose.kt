package com.kevinvi.mangadisplay.jetpack.plugins

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        dependencies {
            add("implementation", libs.findLibrary("androidx-activity-compose").get())

            add("implementation", libs.findLibrary("androidx-compose-ui").get())
            add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())

            add("implementation", libs.findLibrary("androidx-compose-material-icon-core").get())
            add("implementation", libs.findLibrary("androidx-compose-material-icon-extended").get())


            add("debugImplementation", libs.findLibrary("androidx-compose-ui-test-manifest").get())
            add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
        }
    }

}
