dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.moshi:moshi:1.13.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")}

plugins {
    id("org.openapi.generator") version "7.4.0"
    id("maven-publish")
    kotlin("jvm") version "1.9.0"
}

repositories {
    mavenCentral()
}

openApiGenerate {
    generatorName.set("kotlin")
    inputSpec.set("$rootDir/src/main/resources/api/online-store.yaml")
    outputDir.set("$buildDir/generated")
    apiPackage.set("com.example.api")
    modelPackage.set("com.example.model")
    invokerPackage.set("com.example.invoker")
    configOptions.set(
        mapOf("dateLibrary" to "java8")
    )
}

java {
    sourceSets["main"].java.srcDir("$buildDir/generated/src/main/kotlin")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "com.doreamon"
            artifactId = "online-store-api-spec"
            version = "1.0.0"
            from(components["java"])
        }
    }
}

kotlin {
    sourceSets["main"].kotlin.srcDir("build/generated/src/main/kotlin")
}

tasks.named("publishToMavenLocal") {
    dependsOn("openApiGenerate")
}

tasks.named<Jar>("jar") {
    from("$buildDir/generated/src/main/kotlin")
}

tasks.named("compileKotlin") {
    dependsOn("openApiGenerate")
}
