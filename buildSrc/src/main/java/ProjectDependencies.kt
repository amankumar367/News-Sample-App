package dependencies

object Version {

    // android configuration
    const val compileSdk = 31
    const val minSdk = 21
    const val targetSdk = 31

    // Libraries
    const val recyclerView = "1.0.0"
    const val androidx = "1.0.0"
    const val androidxAppCompact = "1.4.0"
    const val constraintLayout = "2.1.2"
    const val swipeRefreshLayout = "1.1.0"
    const val androidx_100beta01 = "1.0.0-beta01"
    const val circleImageView = "3.1.0"
    const val firebaseCore = "17.4.4"
    const val firebaseAnalytics = "18.0.0"
    const val firebaseCrashlytics = "17.2.1"
    const val firebaseCrashlyticsGradle = "2.2.1"
    const val firebaseRemoteConfig = "19.1.4"
    const val materialDesign = "1.4.0"
    const val junit = "4.13.2"
    const val mockitoCore = "2.28.2"
    const val mockitoInline = "2.8.47"
    const val mockitoKotlin = "2.2.0"
    const val assertJCore = "3.2.0"
    const val roomTesting = "1.1.1"
    const val coreTesting = "1.1.1"
    const val roboelectric = "4.2.1"
    const val espressoCore = "3.4.0"
    const val espressoContrib = "3.4.0"
    const val rules = "1.4.0"
    const val runner = "1.4.0"
    const val navigation = "2.3.0"
    const val dagger2 = "2.24"
    const val daggerHilt = "2.40"
    const val lifecycleVersion = "2.0.0"
    const val jacoco = "0.16.0"
    const val jodaTime = "2.10.6"
    const val picasso = "2.71828"
    const val gradleVersion = "4.1.3"
    const val googleServices = "4.3.3"
    const val ktx = "1.7.0"
    const val ktLint = "0.35.0"
    const val kotlinVersion = "1.6.10"
    const val legacySupport = "1.0.0"
    const val dokkaVeresion = "0.9.18"
    const val glide = "4.12.0"
    const val gson = "2.8.6"
    const val lifecycle = "1.1.1"
    const val playCore = "1.7.0"
    const val retrofit = "2.9.0"
    const val retrofit_log = "4.7.1"
    const val room = "2.2.6"
    const val rx = "2.2.16"
    const val rxAndroid = "2.1.1"
    const val rxRelay = "2.0.0"
    const val rxBinding = "3.0.0"
    const val timber = "4.7.1"
    const val message = "21.0.0"
    const val play_services = "18.1.0"
    const val play_plus = "17.0.0"
    const val location = "17.1.0"

    const val coroutinesAndroid = "1.6.0"
    const val coroutinesCore = "1.6.0"

    // plugins versions
    const val detektVersion = "1.9.1"
}


object Dependencies {

    object ClassPaths {
        const val gradleClasspath = "com.android.tools.build:gradle:${Version.gradleVersion}"
        const val googleService = "com.google.gms:google-services:${Version.googleServices}"
        const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-gradle:${Version.firebaseCrashlyticsGradle}"
        const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigation}"
        const val kotlinGradlePluginClasspath = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlinVersion}"
        const val dokkaPluginClasspath = "org.jetbrains.dokka:dokka-gradle-plugin:${Version.dokkaVeresion}"
        const val jacoco = "com.vanniktech:gradle-android-junit-jacoco-plugin:${Version.jacoco}"
        const val daggerHiltClasspath = "com.google.dagger:hilt-android-gradle-plugin:${Version.daggerHilt}"
    }

    object Plugins {
        const val ANDROID_APPLICATION = "com.android.application"
        const val ANDROID_LIBRARY = "com.android.library"
        const val GOOGLE_SERVICES = "com.google.gms.google-services"
        const val FIREBASE_CRASHLYTICS  = "com.google.firebase.crashlytics"
        const val KOTLIN_ANDROID = "kotlin-android"
        const val KOTLIN_KAPT = "kotlin-kapt"
        const val KOTLIN_ANDROID_EXTENSIONS = "kotlin-android-extensions"
        const val SAFE_ARGS = "androidx.navigation.safeargs.kotlin"
        const val DETEKT = "io.gitlab.arturbosch.detekt"
        const val JACOCO = "com.vanniktech.android.junit.jacoco"
    }

    object Lint {
        const val detekt = "io.gitlab.arturbosch.detekt:detekt-cli:${Version.detektVersion}"
        const val ktLint = "com.pinterest:ktlint:${Version.ktLint}"
    }

    object Firebase {
        const val core = "com.google.firebase:firebase-core:${Version.firebaseCore}"
        const val analytics = "com.google.firebase:firebase-analytics:${Version.firebaseAnalytics}"
        const val crashlytics = "com.google.firebase:firebase-crashlytics:${Version.firebaseCrashlytics}"
        const val messaging = "com.google.firebase:firebase-messaging:${Version.message}"
        const val play_services = "com.google.android.gms:play-services-auth:${Version.play_services}"
        const val play_plus = "com.google.android.gms:play-services-plus:${Version.play_plus}"
        const val location = "com.google.android.gms:play-services-location:${Version.location}"
        const val remoteConfig = "com.google.firebase:firebase-config:${Version.firebaseRemoteConfig}"
        const val playCore = "com.google.android.play:core:${Version.playCore}"
    }

    object Lifecycle {
        const val extension = "android.arch.lifecycle:extensions:${Version.lifecycle}"
        const val annotation_compliler = "android.arch.lifecycle:compiler:${Version.lifecycle}"

        // ViewModel and LiveData
        const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
        const val lifeCycleExtension = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycleVersion}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
        const val annotationCompiler = "com.github.bumptech.glide:compiler:${Version.glide}"
    }

    object Kotlin {
        const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlinVersion}"
    }

    object Networking {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val converterGson = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
        const val adapterRxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Version.retrofit}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.retrofit_log}"
        const val okHttp3 = "com.squareup.okhttp3:okhttp:3.12.1"
    }

    object AndroidX {
        const val fragment = "androidx.fragment:fragment:${Version.androidx}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.4.0"
        const val annotation = "androidx.annotation:annotation:${Version.androidx}"
        const val core = "androidx.core:core:${Version.androidx}"
        const val coreKtx = "androidx.core:core-ktx:${Version.ktx}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
        const val materialDesign = "com.google.android.material:material:${Version.materialDesign}"
        const val support_recyclerview_v7 = "androidx.recyclerview:recyclerview:${Version.recyclerView}"
        const val appcompat = "androidx.appcompat:appcompat:${Version.androidxAppCompact}"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefreshLayout}"
        const val vectordrawable = "androidx.vectordrawable:vectordrawable:${Version.androidx_100beta01}"
        const val legacySupport = "androidx.legacy:legacy-support-v4:${Version.legacySupport}"
        const val navigation = "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Version.room}"
        const val roomKtx = "androidx.room:room-ktx:${Version.room}"
        const val roomRxJava2 = "androidx.room:room-rxjava2:${Version.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Version.room}"
        const val roomTesting = "androidx.room:room-testing:${Version.room}"
    }

    object RxJava {
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Version.rxAndroid}"
        const val rxjava2 = "io.reactivex.rxjava2:rxjava:${Version.rx}"
        const val rxrelay2 = "com.jakewharton.rxrelay2:rxrelay:${Version.rxRelay}"
        const val rxBinding = "com.jakewharton.rxbinding3:rxbinding:${Version.rxBinding}"
    }

    object Coroutines {
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesAndroid}"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutinesCore}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutinesCore}"
        const val rx2Interop = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:${Version.coroutinesCore}"
    }

    object Dagger {
        const val dagger2 = "com.google.dagger:dagger:${Version.dagger2}"
        const val daggerAndroid = "com.google.dagger:dagger-android:${Version.dagger2}"
        const val daggerAndroidSupport ="com.google.dagger:dagger-android-support:${Version.dagger2}"
        const val processor = "com.google.dagger:dagger-android-processor:${Version.dagger2}"
        const val compiler = "com.google.dagger:dagger-compiler:${Version.dagger2}"

        /* Dagger Hilt */
        const val hiltAndroid = "com.google.dagger:hilt-android:${Version.daggerHilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.daggerHilt}"
    }

    object Test {
        /* Test Implementation */
        const val junit = "junit:junit:${Version.junit}"
        const val mockitoCore = "org.mockito:mockito-core:${Version.mockitoCore}"
        const val mockitoInline = "org.mockito:mockito-inline:${Version.mockitoInline}"
        const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Version.mockitoKotlin}"
        const val assertJCore = "org.assertj:assertj-core:${Version.assertJCore}"
        const val roomTesting = "android.arch.persistence.room:testing:${Version.roomTesting}"
        const val coreTesting = "android.arch.core:core-testing:${Version.coreTesting}"
        const val roboelectric = "org.robolectric:robolectric:${Version.roboelectric}"

        /* Android Test Implementation */
        const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espressoCore}"
        const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Version.espressoContrib}"
        const val rules = "androidx.test:rules:${Version.rules}"
        const val runner = "androidx.test:runner:${Version.runner}"
    }

    const val gson = "com.google.code.gson:gson:${Version.gson}"
    const val timber = "com.jakewharton.timber:timber:${Version.timber}"
    const val jodaTime = "joda-time:joda-time:${Version.jodaTime}"
    const val picasso = "com.squareup.picasso:picasso:${Version.picasso}"

}
