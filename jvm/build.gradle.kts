plugins {
    id("java-library")
    alias(libs.plugins.kotlinJvm)
    id("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.kotlinx.io)
}

publishing {
    publications {
        withType<MavenPublication> {
            groupId = "hamusuta"
            artifactId = "hamusutax-jvm"

            afterEvaluate {
                from(components["java"])
            }
        }
    }
}
