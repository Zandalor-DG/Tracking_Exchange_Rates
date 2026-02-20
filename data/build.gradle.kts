plugins {
  alias(libs.plugins.convention.android.library)
}

android {
  buildTypes {
    getByName("release") {
      buildConfigField("String", "BASE_URL", AndroidConfig.PROD_BASE_URL)
      buildConfigField("String", "API_KEY", SecretConfig.PROD_API_KEY)
      buildConfigField("Boolean", "USE_MOCK_API", AndroidConfig.USE_MOCK_API_PROD)
    }

    getByName("debug") {
      buildConfigField("String", "BASE_URL", AndroidConfig.DEV_BASE_URL)
      buildConfigField("String", "API_KEY", SecretConfig.DEV_API_KEY)
      buildConfigField("Boolean", "USE_MOCK_API", AndroidConfig.USE_MOCK_API_DEV)
    }
  }
}

dependencies {
  // Retrofit
  api(libs.retrofit)
  implementation(libs.retrofit.converter.kotlinx.serialization)

  // OkHttp
  implementation(platform(libs.okHttp.bom))
  implementation(libs.okHttp)
  implementation(libs.okHttp.loggingInterceptor)

  // Room
  api(libs.androidx.room.runtime)
  ksp(libs.androidx.room.compiler)

  // Security
  implementation(libs.androidx.security.crypto)

  // Tests
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.compose.ui.test.junit4)

  // Hilt
  api(libs.hilt.navigation.compose)
}