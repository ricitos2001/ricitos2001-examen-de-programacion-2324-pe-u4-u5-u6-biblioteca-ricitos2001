plugins {
    kotlin("jvm") version "1.9.22"
    id("org.jetbrains.dokka") version "1.9.20"
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
}

repositories {
    mavenCentral()
    maven("https://repo.gradle.org/gradle/libs-releases-local")
}

var mockKVersion = "1.13.9"

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.8.0")
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.kotest:kotest-property:5.8.0")
    testImplementation("io.kotest:kotest-framework-datatest-jvm:5.8.0")
    testImplementation("io.cucumber:cucumber-java:6.10.4")
    testImplementation("io.cucumber:cucumber-junit:6.10.4")
    testImplementation("io.cucumber:cucumber-spring:6.10.4")
    //testImplementation("io.mockk:mockk:1.13.9")
    testImplementation("io.mockk:mockk:$mockKVersion")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:3.12.0")
    implementation("com.squareup.okio:okio:3.4.0")
    implementation ("io.github.oshai:kotlin-logging-jvm:5.1.0")
    implementation ("org.slf4j:slf4j-api:2.0.12")
    implementation ("ch.qos.logback:logback-classic:1.3.14")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

group = "org.example"
version = "1.0-SNAPSHOT"