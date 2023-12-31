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

}
// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
  version.set("2022.3.3")
  type.set("IC") // Target IDE Platform
  plugins.set(listOf("java"))
}



tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
    options.encoding = "utf-8"
  }

  jar{
    manifest{
      attributes(mapOf("Premain-Class" to "com.example.proagent.byteBuddy.JavaAgentByBuddy"))
    }
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
}
