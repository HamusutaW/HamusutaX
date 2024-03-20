import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-library")
    id("maven-publish")
    alias(libs.plugins.jetbrainsKotlinJvm)
    alias(libs.plugins.jetbrainsKotlinPluginSerialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.annotation)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.freeCompilerArgs += listOf(
        "-opt-in=kotlin.ExperimentalStdlibApi",
        "-opt-in=kotlin.ExperimentalUnsignedTypes",
        "-opt-in=kotlin.io.encoding.ExperimentalEncodingApi"
    )
}

publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}
