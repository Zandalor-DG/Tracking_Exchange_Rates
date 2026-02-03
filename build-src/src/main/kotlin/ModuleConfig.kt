import org.gradle.api.JavaVersion

object LangOptions {
  const val jvmToolchain: Int = 17
  val javaVersion: JavaVersion = JavaVersion.VERSION_17
}

object AndroidConfig {
  const val applicationId = "com.example.to_do"
  const val compileSdk: Int = 36
  const val minSdk: Int = 24
  const val targetSdk: Int = 36
  const val versionCode = 1
  const val versionName = "1.0"

  const val PROD_BASE_URL = "\"https://example.to-do.com/\""
  const val DEV_BASE_URL = "\"https://dev.example.to-do.com/\""
}