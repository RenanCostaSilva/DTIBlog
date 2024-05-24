plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "br.com.renancsdev.dtiblog"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.com.renancsdev.dtiblog"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    dataBinding {
        enable = true
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    //Json
    implementation(libs.converter.gson)
    //koin
    implementation(libs.koin.android)
    implementation(platform("io.insert-koin:koin-bom:3.5.6"))
    implementation(libs.koin.core)
    //LiveData - KXT
    implementation(libs.androidx.lifecycle.livedata.ktx)



    //test - expreso
    implementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.forkingcode.espresso.contrib:espresso-descendant-actions:1.5.0")


    androidTestImplementation(libs.androidx.espresso.intents)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.rules)
    androidTestImplementation(libs.androidx.runner)

    implementation(libs.androidx.espresso.contrib)
    implementation(libs.espresso.descendant.actions)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.legacy.support.v4)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

}