plugins {
    alias(libs.plugins.mangadisplay.android.library)
    alias(libs.plugins.mangadisplay.android.library.compose)
}

android {
    namespace = "com.kevinvi.mangadisplay.core.ui"
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
    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)
    // Core
    implementation(project(":core:data"))
    implementation(project(":core:common"))

    // Material 3
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.animation.graphics)
}