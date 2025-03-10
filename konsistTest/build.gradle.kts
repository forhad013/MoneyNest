plugins {
    id("org.jetbrains.kotlin.jvm")
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    testImplementation(libs.koinsist.core)
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.11.3")
}