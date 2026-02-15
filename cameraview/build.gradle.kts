// Use this build when including cameraview as a source dependency (no publishing, AGP 8+ compatible).
// Copy to: CameraView/cameraview/build.gradle.kts (overwrite when building from GreenCheckmark).
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.otaliastudios.cameraview"
    compileSdk = (findProperty("compileSdkVersion") as? String)?.toIntOrNull() ?: 31
    defaultConfig {
        minSdk = (findProperty("minSdkVersion") as? String)?.toIntOrNull() ?: 15
        targetSdk = (findProperty("targetSdkVersion") as? String)?.toIntOrNull() ?: 31
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["filter"] = "" +
            "com.otaliastudios.cameraview.tools.SdkExcludeFilter," +
            "com.otaliastudios.cameraview.tools.SdkIncludeFilter"
    }
    buildTypes["release"].isMinifyEnabled = false
}

dependencies {
    testImplementation("junit:junit:4.13.1")
    testImplementation("org.mockito:mockito-inline:2.28.2")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("org.mockito:mockito-android:2.28.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    api("androidx.exifinterface:exifinterface:1.3.3")
    api("androidx.lifecycle:lifecycle-common:2.3.1")
    api("com.google.android.gms:play-services-tasks:17.2.1")
    implementation("androidx.annotation:annotation:1.2.0")
    implementation("com.otaliastudios.opengl:egloo:0.6.1")
}
