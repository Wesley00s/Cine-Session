import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt.android)
    kotlin("kapt") version "2.0.20"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20"
    id("com.google.gms.google-services")
}

fun getEnvProperty(key: String): String {
    val properties = Properties()
    val file = rootProject.file(".env")
    if (file.exists()) {
        properties.load(file.inputStream())
    }
    return properties.getProperty(key) ?: throw IllegalArgumentException("Key not found: $key")
}

android {
    namespace = "com.example.cine.session"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.cine.session"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "API_KEY", "\"${getEnvProperty("API_KEY")}\"")
        buildConfigField("String", "BASE_URL", "\"${getEnvProperty("BASE_URL")}\"")
        buildConfigField("String", "ACCOUNT_ID", "\"${getEnvProperty("ACCOUNT_ID")}\"")

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        buildFeatures {
            buildConfig = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    implementation(libs.navigation.compose)

    implementation(libs.kotlin.serialization)
    implementation(libs.ktor.ktor.client.android)
    implementation(libs.ktor.ktor.client.content.negotiation)
    implementation(libs.ktor.ktor.client.logging)
    implementation(libs.ktor.ktor.client.serialization)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.dagger.hilt)
    implementation(libs.play.services.auth)
    implementation(libs.firebase.auth)
    kapt(libs.dagger.hilt.compiler)

    implementation(libs.dotenv.kotlin)

    implementation(libs.firebase.analytics)
    implementation(platform(libs.firebase.bom))

    implementation(libs.firebase.auth.ktx)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(kotlin("reflect"))
}