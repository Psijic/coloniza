package com.psvoid.coloniza.buildsrc

import org.gradle.api.JavaVersion

object Versions {
    const val ktlint = "0.43.2"
    const val jvm = "11"
    val java = JavaVersion.VERSION_11
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.2.1"
    const val jdkDesugar = "com.android.tools:desugar_jdk_libs:1.1.5"

    const val junit = "org.junit.jupiter:junit-jupiter-api:5.7.2"

    object Accompanist {
        private const val version = "0.23.1"
        const val insets = "com.google.accompanist:accompanist-insets:$version"
        const val permissions = "com.google.accompanist:accompanist-permissions:$version"
    }

    object Kotlin {
        const val version = "1.6.21"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.6.0"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Log {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
    }

    object Network {
        object Ktor {
            private const val version = "1.6.8"
            const val core = "io.ktor:ktor-client-core:$version"
            const val cio = "io.ktor:ktor-client-cio:$version"
            const val json = "io.ktor:ktor-client-json:$version"
            const val serialization = "io.ktor:ktor-client-serialization:$version"
            const val logging = "io.ktor:ktor-client-logging:$version"
            const val test = "io.ktor:ktor-server-test-host:$version"
        }
    }

    object Google {
        object Maps {
            const val mapsCompose = "com.google.maps.android:maps-compose:1.2.0"
        }

        object PlayServices {
            const val location = "com.google.android.gms:play-services-location:16.0.0"
            const val playServicesMaps = "com.google.android.gms:play-services-maps:18.0.2"
            // Integration with Google Play Services Tasks API.
            const val coroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.0"
        }
    }

    object Extra {
        const val ratingBar = "io.github.a914-gowtham:compose-ratingbar:1.1.0"
    }

    object Database {
        private const val version = "2.4.2"
        const val roomRuntime = "androidx.room:room-runtime:$version"
        const val roomCompiler = "androidx.room:room-compiler:$version"
        const val roomKtx = "androidx.room:room-ktx:$version"
    }

    object Firebase {
        const val database = "com.google.firebase:firebase-database-ktx:20.0.4"
        const val analytics = "com.google.firebase:firebase-analytics:20.1.0"
        const val storage = "com.google.firebase:firebase-storage:20.0.1"
        const val messaging = "com.google.firebase:firebase-messaging:23.0.1"
        const val auth = "com.google.firebase:firebase-auth:21.0.2"
        const val config = "com.google.firebase:firebase-config:21.0.2"
        const val uiAuth = "com.firebaseui:firebase-ui-auth:6.2.1"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.4.1"
        const val coreKtx = "androidx.core:core-ktx:1.7.0"

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.4.0"
        }

        object Compose {
            const val snapshot = ""
            const val version = "1.2.0-beta03"

            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val material = "androidx.compose.material:material:$version"
            const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
            const val test = "androidx.compose.ui:ui-test:$version"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
            const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$version"
            const val uiUtil = "androidx.compose.ui:ui-util:$version"
            const val viewBinding = "androidx.compose.ui:ui-viewbinding:$version"

            const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0"
            const val navigation = "androidx.navigation:navigation-compose:2.4.1"

            object Material3 {
                private const val version = "1.0.0-alpha10"
                const val snapshot = ""
                const val material3 = "androidx.compose.material3:material3:$version"
            }

            object Media {
                const val coil = "io.coil-kt:coil-compose:2.0.0-rc03"
            }
        }

        object Test {
            private const val version = "1.4.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.2"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
        }

        object Lifecycle {
            private const val version = "2.4.0"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
        }

        //TODO: Remove
        object Navigation {
            private const val version = "2.4.0"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
        }

    }

}

object Urls {
    const val composeSnapshotRepo = "https://androidx.dev/snapshots/builds/" +
            "${Libs.AndroidX.Compose.snapshot}/artifacts/repository/"
    const val composeMaterial3SnapshotRepo = "https://androidx.dev/snapshots/builds/" +
            "${Libs.AndroidX.Compose.Material3.snapshot}/artifacts/repository/"
    const val accompanistSnapshotRepo = "https://oss.sonatype.org/content/repositories/snapshots"
}
