plugins {
	id("com.android.application")
}

android {
	namespace = "com.corwin.testtrx"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.corwin.testtrx"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
}

dependencies {

	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.11.0")
	implementation("androidx.constraintlayout:constraintlayout:2.1.4")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
