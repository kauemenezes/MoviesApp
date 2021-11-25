object Versions {
    const val gradle = "4.2.0"
    const val kotlinVersion = "1.4.32"
    const val buildToolsVersion = "30.0.2"
    const val compileSdkVersion = 31
    const val minSdkVersion = 21
    const val targetSdkVersion = 31

    // Dependencies Version - Presentation
    const val appCompat = "1.4.0"
    const val coreKtx = "1.7.0"
    const val constraintLayout = "2.1.2"
    const val swipeRefreshLayout = "1.1.0"
    const val materialDesign = "1.4.0"
    const val koin = "3.1.3"
    const val lifecycleVersion = "2.4.0"
    const val pagingRuntime = "3.1.0"
    const val coroutines = "1.5.1"
    const val navigation = "2.3.5"
    const val glide = "4.10.0"

    // Data
    const val retrofit = "2.7.1"
    const val room = "2.3.0"
    const val loggingInterceptor = "4.9.1"

    // Testing
    const val junit = "4.13.2"
    const val espresso = "3.4.0"
    const val androidxJunit = "1.1.3"
    const val runner = "1.2.0"
    const val truth = "1.0"
    const val mockito = "3.3.1"
    const val mockitoInline = "2.13.0"
    const val mockWebServer = "4.2.1"
    const val robolectric = "4.3.1"
    const val robolectricMultidex = "4.0.1"
    const val testCore = "1.4.0"
    const val rules = "1.2.0"
    const val archComponentTest = "2.1.0"
    const val hamcrest = "1.3"
    const val mockk = "1.10.0"
}

object Build {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val MATERIAL_DESIGN = "com.google.android.material:material:${Versions.materialDesign}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycleVersion}"
    const val VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    const val LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
    const val LIFECYCLE_COMMOM_JAVA8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleVersion}"
    const val NAVIGATION_GRADLE_PLUGIN = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val PAGING = "androidx.paging:paging-runtime-ktx:${Versions.pagingRuntime}"
    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.room}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.room}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.room}"
    const val KOIN = "io.insert-koin:koin-android:${Versions.koin}"
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val JUNIT = "junit:junit:${Versions.junit}"
    const val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.robolectric}"
    const val ROBOLECTRIC_MULTIDEX = "org.robolectric:shadows-multidex:${Versions.robolectricMultidex}"
    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${Versions.androidxJunit}"
    const val ANDROIDX_JUNIT_KTX = "androidx.test.ext:junit-ktx:${Versions.androidxJunit}"
    const val ANDROIDX_TEST_CORE = "androidx.test:core:${Versions.testCore}"
    const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:${Versions.runner}"
    const val HAMCREST = "org.hamcrest:hamcrest-all:${Versions.hamcrest}"
    const val ARCH_COMPONENT = "androidx.arch.core:core-testing:${Versions.archComponentTest}"
    const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val MOCKITO = "org.mockito:mockito-core:${Versions.mockito}"
    const val MOCKITO_INLINE = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    const val MOCKITO_ANDROID = "org.mockito:mockito-android:${Versions.mockito}"
    const val MOCKK = "io.mockk:mockk:${Versions.mockk}"
    const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val ESPRESSO_INTENTS = "androidx.test.espresso:espresso-intents:${Versions.espresso}"

    const val GRADLE = "com.android.tools.build:gradle:${Versions.gradle}"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
}

object Modules {
    const val DATA = ":data"
    const val DOMAIN = ":domain"
}