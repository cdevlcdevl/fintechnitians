plugins {
	java
	id("org.springframework.boot") version "3.3.1"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.openapi.generator") version "7.7.0"

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
var genAiVersion = "1.9.0"
var swaggerVersion = "2.1.19"
var jacksonVersion = "0.2.6"

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

	implementation("com.google.genai:google-genai:$genAiVersion")

	implementation("io.swagger.parser.v3:swagger-parser:$swaggerVersion")
	implementation("org.openapitools:jackson-databind-nullable:$jacksonVersion")
	implementation("org.springframework.kafka:spring-kafka")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

val generateApi by tasks.registering(org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
//openApiGenerate {
	generatorName.set("spring")
	inputSpec.set("$projectDir/src/main/resources/api/finclusion.yaml")
	outputDir.set("$projectDir/build/generated/api")
	apiPackage.set("com.hackathon.finclusion.api")
	modelPackage.set("com.hackathon.finclusion.model")
	modelNameSuffix.set("Dto")

	// Fine-tune the generated code for a modern Spring Boot application
	configOptions.set(mapOf(
		"dateLibrary" to "java8",
		"hideGenerationTimestamp" to "true",
		"interfaceOnly" to "true",          // Generate only interfaces, not full controllers
		"swaggerAnnotations" to "true",
		"useSpringBoot3" to "true",         // Use Spring Boot 3 specific features
		"useResponseEntity" to "true",      // Wrap responses in ResponseEntity for full control
		"openApiNullable" to "false"        // Use `Optional<>` instead of a custom `JsonNullable` wrapper
	))
}

tasks.compileJava {
	dependsOn(generateApi)
}
sourceSets["main"].java.srcDir("$projectDir/build/generated/api/src/main/java")

tasks.withType<Test> {
	useJUnitPlatform()
}