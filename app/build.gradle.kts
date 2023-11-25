plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.ar_project"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ar_project"
        minSdk = 24
        targetSdk = 34
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

        // Provides ARCore Session and related resources.
        implementation("com.google.ar:core:1.40.0")
        // Provides ArFragment, and other UX resources.
        implementation("com.google.ar.sceneform.ux:sceneform-ux:1.15.0")
        implementation("com.google.ar.sceneform:core:1.15.0")
        implementation("com.google.ar.sceneform:assets:1.17.1")
        implementation ("com.google.android.material:material:1.5.0-alpha04")

}