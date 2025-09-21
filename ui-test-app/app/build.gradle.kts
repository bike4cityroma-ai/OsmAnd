plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "net.osmand"
    compileSdk = 34

    defaultConfig {
        applicationId = "net.osmand"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    // 👉 Flavor per branding OsmAnd + TheUrbeBike
    flavorDimensions += listOf("branding")

    productFlavors {
        create("osmand") {
            dimension = "branding"
            applicationId = "net.osmand"
        }
        create("theurbebike") {
            dimension = "branding"
            applicationId = "org.bike4city.theurbebike"
            versionNameSuffix = "-tub"
            resValue("string", "app_name", "TheUrbeBike")
            manifestPlaceholders["appLabel"] = "TheUrbeBike"
        }
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
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.activity:activity-compose:1.8.2")
}
