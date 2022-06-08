// Top-level build file where you can add configuration options common to all sub-projects/modules.
//import com.psvoid.coloniza.buildsrc.Libs

plugins {
    //id ("com.diffplug.spotless") version "6.2.0"
}
buildscript {
    repositories {
        google()
        mavenCentral()

//        if (com.psvoid.coloniza.buildsrc.Libs.AndroidX.Compose.Material3.snapshot.isNotEmpty()) {
//            maven { setUrl(com.psvoid.coloniza.buildsrc.Urls.composeMaterial3SnapshotRepo) }
//        }
//        if (com.psvoid.coloniza.buildsrc.Libs.AndroidX.Compose.snapshot.isNotEmpty()) {
//            maven { setUrl(com.psvoid.coloniza.buildsrc.Urls.composeSnapshotRepo) }
//        }
//        if (com.psvoid.coloniza.buildsrc.Libs.Accompanist.version.endsWith("SNAPSHOT")) {
//            maven { setUrl("https://oss.sonatype.org/content/repositories/snapshots/") }
//        }
    }
    dependencies {
        classpath(com.psvoid.coloniza.buildsrc.Libs.androidGradlePlugin)
        classpath(com.psvoid.coloniza.buildsrc.Libs.Kotlin.gradlePlugin)
//        classpath("com.android.tools.build:gradle:$androidGradlePluginVersion")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.gms:google-services:4.3.10")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }

    allprojects {
        repositories {
            google()
            mavenCentral()
        }
    }

    tasks.register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}