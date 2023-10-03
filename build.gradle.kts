plugins {
    id("java")
    id("application")
}

application {
    mainClass.set("Application")
}

group = "a042807"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.googlecode.lanterna",  "lanterna",  "3.1.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}