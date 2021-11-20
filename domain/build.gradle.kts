plugins {
    id("java-library")
    id("kotlin")
    kotlin("kapt")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Build.KOTLIN)

    // -- Coroutines
    implementation(Build.COROUTINES_CORE)
    implementation(Build.COROUTINES_ANDROID)

    // -- Dagger
    implementation(Build.DAGGER)
    kapt(Build.DAGGER_COMPILER)
}

//sourceCompatibility = "8"
//targetCompatibility = "8"