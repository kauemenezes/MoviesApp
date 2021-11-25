plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles = "consumer-rules.pro"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    api(project(Modules.DOMAIN))
    implementation(Build.KOTLIN)

    // -- Retrofit2
    implementation(Build.RETROFIT)
    implementation(Build.RETROFIT_CONVERTER_GSON)

    // -- Lifecycle Components (ViewModel, LiveData and ReactiveStreams)
    implementation(Build.LIVEDATA_KTX)

    // -- Paging
    implementation(Build.PAGING)

    // -- Coroutines
    implementation(Build.COROUTINES_CORE)
    implementation(Build.COROUTINES_ANDROID)

    // -- Room
    implementation(Build.ROOM_RUNTIME)
    kapt(Build.ROOM_COMPILER)
    // Kotlin Extensions and Coroutines support for Room
    implementation(Build.ROOM_KTX)
}