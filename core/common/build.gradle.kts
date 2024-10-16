plugins {
    alias(libs.plugins.mangadisplay.android.library)
    alias(libs.plugins.mangadisplay.android.library.compose)
    alias(libs.plugins.mangadisplay.android.hilt)
}

android {
    namespace = "com.kevinvi.mangadisplay.core.common"
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
    // AndroidX Core
    implementation(libs.androidx.core.ktx)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit)
}