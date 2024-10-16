import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.kevinvi.mangadisplay.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "mangadisplay.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidApplicationCompose") {
            id = "mangadisplay.android.application.compose"
            implementationClass = "AndroidApplicationComposePlugin"
        }
        register("androidApplicationFirebase") {
            id = "mangadisplay.android.application.firebase"
            implementationClass = "AndroidApplicationFirebasePlugin"
        }
        register("androidFeature") {
            id = "mangadisplay.android.feature"
            implementationClass = "AndroidFeaturePlugin"
        }
        register("androidFeatureCompose") {
            id = "mangadisplay.android.feature.compose"
            implementationClass = "AndroidFeatureComposePlugin"
        }
        register("androidHilt") {
            id = "mangadisplay.android.hilt"
            implementationClass = "AndroidHiltPlugin"
        }
        register("androidLibrary") {
            id = "mangadisplay.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("androidLibraryCompose") {
            id = "mangadisplay.android.library.compose"
            implementationClass = "AndroidLibraryComposePlugin"
        }
        register("androidXRoom") {
            id = "mangadisplay.androidx.room"
            implementationClass = "AndroidRoomPlugin"
        }
    }
}