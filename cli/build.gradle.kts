plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    application
    id("com.github.johnrengelman.shadow")
    id("io.github.sgtsilvio.gradle.proguard")
}

dependencies {
    implementation(project(":core"))
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.6")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")
}

application {
    mainClass.set("dev.keiji.license.maven.MainKt")
}

val shadowJar = tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveClassifier.set("all-raw")
}

val proguardJar = tasks.register<io.github.sgtsilvio.gradle.proguard.ProguardTask>("proguardJar") {
    dependsOn(shadowJar)
    addInput {
        classpath.from(shadowJar.get().archiveFile)
    }
    addLibrary {
        val javaHome = System.getProperty("java.home")
        if (file("$javaHome/jmods/java.base.jmod").exists()) {
            classpath.from(fileTree("$javaHome/jmods/") { include("*.jmod") })
        } else if (file("$javaHome/lib/jrt-fs.jar").exists()) {
            classpath.from(file("$javaHome/lib/jrt-fs.jar"))
        } else if (org.gradle.internal.os.OperatingSystem.current().isMacOsX) {
            classpath.from(file("$javaHome/../Classes/classes.jar"))
        } else {
            classpath.from(file("$javaHome/lib/rt.jar"))
            classpath.from(file("$javaHome/lib/jce.jar"))
        }
    }
    addOutput {
        archiveFile.set(layout.buildDirectory.file("libs/gradle-license-generator-${project.version}-all.jar"))
    }
    rules.addAll(project.file("proguard-rules.pro").readLines())
}
