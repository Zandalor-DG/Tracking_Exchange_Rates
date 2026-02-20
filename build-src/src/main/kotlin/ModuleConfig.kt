import org.gradle.api.JavaVersion

object LangOptions {
  const val JVM_TOOLCHAIN: Int = 17
  val javaVersion: JavaVersion = JavaVersion.VERSION_17
}

object AndroidConfig {
  const val APPLICATION_ID = "com.paliy_dmitriy.tracking_exchange_rates"
  const val COMPILE_SDK: Int = 36
  const val MIN_SDK: Int = 26
  const val TARGET_SDK: Int = 36
  const val VERSION_CODE = 1
  const val VERSION_NAME = "1.0"

  const val USE_MOCK_API_PROD = "false"
  const val USE_MOCK_API_DEV = "true"

  const val PROD_BASE_URL = "\"https://api.apilayer.com/\""
  const val DEV_BASE_URL = "\"https://api.apilayer.com/\""
}