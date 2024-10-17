plugins {
    alias(libs.plugins.mangadisplay.android.library)
    alias(libs.plugins.mangadisplay.android.library.compose)
    alias(libs.plugins.mangadisplay.android.hilt)

    id("kotlin-parcelize")

    kotlin("plugin.serialization") version libs.versions.kotlin.get()

}

android {
    namespace = "com.kevinv.mangadisplay.core.data"
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

    // AndroidX Core
    implementation(libs.androidx.core.ktx)
    // Ktor
    implementation(libs.bundles.ktor)
    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit)
}