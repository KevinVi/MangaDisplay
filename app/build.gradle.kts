plugins {
    alias(libs.plugins.mangadisplay.android.application)
    alias(libs.plugins.mangadisplay.android.application.compose)
    alias(libs.plugins.mangadisplay.android.hilt)
}

android {
    namespace = "com.kevinvi.mangadisplay"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kevinvi.mangadisplay"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Core
    implementation(project(":core:common"))
    implementation(project(":core:ui"))

    // Feature
    implementation(project(":feature:popular"))
}