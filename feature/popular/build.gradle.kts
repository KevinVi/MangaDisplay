plugins {
    alias(libs.plugins.mangadisplay.android.feature)
    alias(libs.plugins.mangadisplay.android.feature.compose)
    alias(libs.plugins.mangadisplay.android.hilt)

    id("kotlin-parcelize")
}

android {
    namespace = "com.kevinvi.mangadisplay.feature.popular"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    // Core
    implementation(project(":core:common"))
    implementation(project(":core:ui"))

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit)
}