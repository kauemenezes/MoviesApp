plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    buildToolsVersion(Versions.buildToolsVersion)

    defaultConfig {
        applicationId = "br.com.moviesapp"
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
//            testCoverageEnabled = true
            applicationIdSuffix = ".debug"
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

//    testBuildType = "debug"
    testOptions {
        unitTests.apply {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(Modules.DATA))

    implementation(Build.KOTLIN)
    implementation(Build.APPCOMPAT)
    implementation(Build.CORE_KTX)
    implementation(Build.CONSTRAINT_LAYOUT)
    implementation(Build.SWIPE_REFRESH_LAYOUT)

    // -- Material
    implementation(Build.MATERIAL_DESIGN)

    // -- Retrofit2
    implementation(Build.RETROFIT)
    implementation(Build.RETROFIT_CONVERTER_GSON)
    implementation(Build.LOGGING_INTERCEPTOR)

    // -- Lifecycle Components (ViewModel, LiveData and ReactiveStreams)
    implementation(Build.LIFECYCLE_RUNTIME)
    implementation(Build.VIEWMODEL_KTX)
    implementation(Build.LIVEDATA_KTX)
    implementation(Build.LIFECYCLE_COMMOM_JAVA8)

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

    // -- Koin
    implementation(Build.KOIN)

    // Navigation
    implementation(Build.NAVIGATION_FRAGMENT)
    implementation(Build.NAVIGATION_UI)

    //Glide
    implementation(Build.GLIDE)
    kapt(Build.GLIDE_COMPILER)

    // Test
    testImplementation(Build.JUNIT)
    testImplementation(Build.ROBOLECTRIC)
    testImplementation(Build.ROBOLECTRIC_MULTIDEX)
    testImplementation(Build.ANDROIDX_TEST_CORE)
    testImplementation(Build.ANDROIDX_TEST_RUNNER)
    testImplementation(Build.ANDROIDX_JUNIT)
    testImplementation(Build.HAMCREST)
    testImplementation(Build.ARCH_COMPONENT)
    testImplementation(Build.COROUTINES_TEST)
    testImplementation(Build.MOCKITO)
    testImplementation(Build.MOCKITO_INLINE)
    testImplementation(Build.MOCKK)
    testImplementation(Build.MOCK_WEB_SERVER)

    androidTestImplementation(Build.MOCKITO_ANDROID)
    androidTestImplementation(Build.ANDROIDX_TEST_CORE)
    androidTestImplementation(Build.ANDROIDX_TEST_RUNNER)
    androidTestImplementation(Build.ESPRESSO)
    androidTestImplementation(Build.ESPRESSO_INTENTS)
    androidTestImplementation(Build.ANDROIDX_JUNIT_KTX)
    androidTestImplementation(Build.MOCK_WEB_SERVER)
}