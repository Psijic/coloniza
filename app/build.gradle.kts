import com.psvoid.coloniza.buildsrc.Libs
import com.psvoid.coloniza.buildsrc.Versions
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.plugin.serialization") version com.psvoid.coloniza.buildsrc.Libs.Kotlin.version
    id("kotlin-kapt")
    id("com.google.gms.google-services") // Need for Firebase init
}

apply("passwords.gradle")
val googleMapsKey: String by project

// Create a variable called keystorePropertiesFile, and initialize it to your keystore.properties file, in the root folder.
val keystorePropertiesFile = rootProject.file("keystore.properties")
// Initialize a new Properties() object called keystoreProperties.
val keystoreProperties = Properties()
// Load the keystore.properties file into the keystoreProperties object.
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
            storeFile = file(keystoreProperties.getProperty("storeFile"))
            storePassword = keystoreProperties.getProperty("storePassword")
        }
    }
    compileSdk = 32
    buildToolsVersion = "32.0.0"

    buildFeatures {
        viewBinding = true
        compose = true
        dataBinding = true //TODO: Remove
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Libs.AndroidX.Compose.version
    }

    defaultConfig {
        applicationId = "com.psvoid.coloniza"
        minSdk = 26
        targetSdk = 31
        versionCode = 2
        versionName = "1.0"
        setProperty("archivesBaseName", "$applicationId-$versionName.$versionCode") //rename the artifact

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        manifestPlaceholders["google_maps_key"] = googleMapsKey
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {

        }
    }

    // Product flavors represent different versions of the app https://developer.android.com/studio/build#variant_output
//    flavorDimensions.add("tier")
//    productFlavors {
//        create("free") {
//            dimension = "tier"
//            applicationId = "com.example.myapp.free"
//        }
//
//        create("paid") {
//            dimension = "tier"
//            applicationId = "com.example.myapp.paid"
//        }
//    }

//    applicationVariants.all{
//        outputs.all {
//            if(name.contains("release"))
//                (this as BaseVariantOutputImpl).outputFileName = "$name-$versionName.$versionCode.apk"
//        }
//    }

    compileOptions {
        sourceCompatibility(Versions.java)
        targetCompatibility(Versions.java)
    }

    kotlinOptions {
        jvmTarget = Versions.jvm
    }

}

dependencies {
    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Coroutines.android)

    implementation(Libs.AndroidX.Activity.activityCompose)
    implementation(Libs.AndroidX.coreKtx)

    implementation(Libs.AndroidX.Lifecycle.livedata)
    implementation(Libs.AndroidX.Lifecycle.viewModelCompose)
    implementation(Libs.AndroidX.Lifecycle.viewModelKtx)

    //Navigation
//    implementation(Libs.AndroidX.Navigation.fragment)
    implementation(Libs.AndroidX.Navigation.uiKtx)

    //Compose
    implementation(Libs.AndroidX.Compose.layout)
    // TODO: (M3) Remove this dependency when all components are available
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.Material3.material3)
    implementation(Libs.AndroidX.Compose.materialIconsExtended)
    implementation(Libs.AndroidX.Compose.toolingPreview)
    implementation(Libs.AndroidX.Compose.uiUtil)
    implementation(Libs.AndroidX.Compose.runtime)
//    implementation(Libs.AndroidX.Compose.runtimeLivedata)
//    implementation(Libs.AndroidX.Compose.viewBinding)
//    implementation(Libs.AndroidX.Compose.constraintLayout)
    implementation(Libs.AndroidX.Compose.navigation)
    debugImplementation(Libs.AndroidX.Compose.tooling)
    debugImplementation(Libs.AndroidX.Compose.uiTestManifest)

    //Accompanist
    implementation(Libs.Accompanist.insets)
    implementation(Libs.Accompanist.permissions)

    //Tests
    testImplementation(Libs.junit)
    androidTestImplementation(Libs.AndroidX.Test.core)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)
    androidTestImplementation(Libs.AndroidX.Test.rules)
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Compose.uiTest)

    //Log
    implementation(Libs.Log.timber)

    //Ktor
    implementation(Libs.Network.Ktor.core)
    implementation(Libs.Network.Ktor.cio)
    implementation(Libs.Network.Ktor.json)
    implementation(Libs.Network.Ktor.serialization)
    implementation(Libs.Network.Ktor.logging)

    //Extra
//    implementation(Libs.Extra.ratingBar)

    // Firebase
//    implementation(Libs.Firebase.database)

    // Room
    implementation(Libs.Database.roomRuntime)
    implementation(Libs.Database.roomKtx)
    kapt(Libs.Database.roomCompiler)

    // Maps
//    implementation (Libs.Google.Maps.mapsCompose)

    //Play Services
    implementation (Libs.Google.PlayServices.playServicesMaps)
    implementation (Libs.Google.PlayServices.location)
    implementation (Libs.Google.PlayServices.coroutinesPlayServices)

    //Media
    implementation (Libs.AndroidX.Compose.Media.coil)
}