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
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}