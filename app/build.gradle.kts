import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName
import java.text.SimpleDateFormat
import java.util.Calendar

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.android.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.gms.googleServices)
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

fun getDate(): String {
    val format = "HH\'h\'-dd"
    val current = Calendar.getInstance().time
    return SimpleDateFormat(format).format(current)
}

android {
    namespace = "news.app.graduation"
    compileSdk = 34

    defaultConfig {
        applicationId = "news.app.graduation"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0-init project"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        archivesName.set("NewsAppGraduation-($versionCode-$versionName)${getDate()}")
    }

    buildTypes {
        debug {

        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    dataBinding {
        enable = true
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
    kapt {
        correctErrorTypes = true
    }
    flavorDimensions += "environment"
    productFlavors {
        create("development") {
            dimension = "environment"
            manifestPlaceholders["appLabel"] = "News App Graduation Debug"
            buildConfigField("String", "BASE_URL", "\"https://thanhnien.vn/rss/\"")
        }
        create("production") {
            dimension = "environment"
            manifestPlaceholders["appLabel"] = "News App Graduation"
            buildConfigField("String", "BASE_URL", "\"https://thanhnien.vn/rss/\"")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.viewpager2)
    implementation(libs.material)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //dagger-hilt
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)

    //gson
    implementation(libs.gson)

    //OkHttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    //chucker
    debugImplementation(libs.chucker.logging.debug)
    releaseImplementation(libs.chucker.logging.release)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.converter.scalars)
    implementation(libs.retrofit.converter.simplexml)

    //coroutine
    implementation(libs.coroutine.android)
    implementation(libs.coroutine.core)

    //timber log
    implementation(libs.timber.log)

    //ssp, sdp
    implementation(libs.ssp.android)
    implementation(libs.sdp.android)

    //swipe refresh layout
    implementation(libs.swipe.refresh.layout)

    //glide
    implementation(libs.glide)

    //ktx
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)

    //room
    implementation(libs.room)
    kapt(libs.room.compiler)

    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.dynamic.links)
}