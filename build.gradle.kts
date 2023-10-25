plugins {
    id("java")
    id("application")
    id ("groovy")
}

application {
    mainClass.set("Application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.googlecode.lanterna","lanterna","3.2.0-alpha1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation ("org.mockito:mockito-core:3.7.7")
    testImplementation ("org.spockframework:spock-core:2.0-groovy-3.0")
    testImplementation ("org.codehaus.groovy:groovy-all:3.0.8")
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
