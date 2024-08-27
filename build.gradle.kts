plugins {
    kotlin("jvm") version "2.0.20"
}

group = "me.matin.mlib"
version = "1.0"

repositories {
    mavenCentral()
}

val java = 21

kotlin {
    jvmToolchain(java)
}