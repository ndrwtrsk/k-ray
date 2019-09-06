import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.41"
}

group = "nd.rw"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.6")
//    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.2")
    testCompile("com.winterbe:expekt:0.5.0")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.0.6")
    testRuntimeOnly(kotlin("reflect"))
}

tasks {
    test {
        useJUnitPlatform {
            includeEngines("spek2")
        }
    }
}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}