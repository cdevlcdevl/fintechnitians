plugins {
	java
	id("org.springframework.boot") version "3.3.1"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.hackathon"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

// Use the latest stable GA version of Spring AI
val springAiVersion = "1.0.5"

repositories {
	mavenCentral()
}

dependencies {
	// Core Spring Boot Starters
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-websocket")
	developmentOnly("org.springframework.boot:spring-boot-devtools") // For live reload

	// Database Drivers
	runtimeOnly("org.postgresql:postgresql")
	runtimeOnly("com.h2database:h2")

	implementation("com.google.genai:google-genai:1.9.0")
	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}