println("I'm now compiling with gradle " + project.gradle.gradleVersion);
plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.12.0"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies{
    implementation(project(":pro-agent"))
    implementation("net.bytebuddy:byte-buddy:1.14.5")
    implementation("net.bytebuddy:byte-buddy-agent:1.14.5")
    implementation("javassist:javassist:3.12.1.GA")
    implementation("cn.hutool:hutool-all:5.8.18")
}
// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2022.1.4")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf("java"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
        options.encoding = "utf-8"
    }
    patchPluginXml {
        sinceBuild.set("221")
        untilBuild.set("231.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

    jar{
        manifest{
            attributes(mapOf("Premain-Class" to "com.example.calculateplugin.agent.JavaAgentByBuddy"))
        }
    }
}
