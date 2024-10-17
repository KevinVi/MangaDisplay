plugins {
    alias(libs.plugins.mangadisplay.android.feature)
    alias(libs.plugins.mangadisplay.android.feature.compose)
    alias(libs.plugins.mangadisplay.android.hilt)

    id("kotlin-parcelize")

    kotlin("plugin.serialization") version libs.versions.kotlin.get()
}

android {
    namespace = "com.kevinvi.search"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.runtime)

    // Ktor
    implementation(libs.bundles.ktor)

    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)

    // Core
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit)
}