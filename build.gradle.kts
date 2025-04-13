plugins {
    id("org.openapi.generator") version "7.4.0"
}

repositories {
    mavenCentral()
}

openApiGenerate {
    generatorName.set("kotlin")  // Указываем тип генератора
    inputSpec.set("$rootDir/src/main/resources/api/online-store.yaml")
    outputDir.set("$buildDir/generated")
    apiPackage.set("com.example.api")
    modelPackage.set("com.example.model")
    invokerPackage.set("com.example.invoker")
    configOptions.set(
        mapOf(
            "dateLibrary" to "java8"
        )
    )
}
