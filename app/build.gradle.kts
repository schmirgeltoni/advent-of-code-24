plugins {
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    implementation(libs.jetbrains.kotlin.stdlib)

}

kotlin {
    jvmToolchain(17)
}