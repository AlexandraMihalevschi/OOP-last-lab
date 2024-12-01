plugins {
    kotlin("jvm") version "1.9.0" // Adjust Kotlin version as needed
}

repositories {
    mavenCentral()
    useJUnitPlatform()
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    testImplementation(kotlin("test"))
    testImplementation "org.jetbrains.kotlin:kotlin-test-junit5:1.8.10"
}
