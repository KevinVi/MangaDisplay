// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose.compiler) apply false
    alias(libs.plugins.google.devtools.ksp) apply false
    alias(libs.plugins.hilt) apply false

    kotlin("plugin.serialization") version libs.versions.kotlin.get() apply false
    alias(libs.plugins.version.catalog.update)
    alias(libs.plugins.android.test) apply false
}
