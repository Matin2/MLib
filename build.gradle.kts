plugins {
    kotlin("jvm") version "2.1.10"
}

group = "me.matin.mlib"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}