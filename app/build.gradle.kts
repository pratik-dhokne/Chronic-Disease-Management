plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.pratik.chronicdisease"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pratik.chronicdisease"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.3"))
    implementation("com.firebaseui:firebase-ui-database:7.1.1")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-storage:19.1.1")

    //Shimmer Effect
    implementation ("com.facebook.shimmer:shimmer:0.5.0")



    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.19")

}