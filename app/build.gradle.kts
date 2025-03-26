plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.likhit.cryptotracker"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.likhit.cryptotracker"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField(
                "String", "BASE_URL", "\"https://api.coincap.io/v2/\""
            )
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField(
                "String", "BASE_URL", "\"https://api.coincap.io/v2/\""
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)//
    implementation(libs.androidx.lifecycle.runtime.ktx)//
    implementation(libs.androidx.activity.compose)//
    implementation(platform(libs.androidx.compose.bom))//
    implementation(libs.androidx.ui)//
    implementation(libs.androidx.ui.graphics)//
    implementation(libs.androidx.ui.tooling.preview)//
    implementation(libs.androidx.material3)//
    testImplementation(libs.junit)//
    androidTestImplementation(libs.androidx.junit)//
    androidTestImplementation(libs.androidx.espresso.core)//
    androidTestImplementation(platform(libs.androidx.compose.bom))//
    androidTestImplementation(libs.androidx.ui.test.junit4)//
    debugImplementation(libs.androidx.ui.tooling)//
    debugImplementation(libs.androidx.ui.test.manifest)//

    //Compose
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.compose.material3.adaptive.navigation)
    implementation(libs.compose.material.icons.extended)

    //Desugar
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    //Koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    //Ktor
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.kotlinx.json)
}