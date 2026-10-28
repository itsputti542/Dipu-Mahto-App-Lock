plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.dipumahto.applock"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.dipumahto.applock"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
}
dependencies {
    implementation("androidx.biometric:biometric:1.1.0")
    implementation("androidx.core:core-ktx:1.15.0")
}
