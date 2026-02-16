plugins {
  alias(libs.plugins.convention.android.app)
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
}

android {
  signingConfigs {
    create("release") {
      if (project.hasProperty("storeFile")) {
        storeFile = file(project.properties["storeFile"] as String)
        storePassword = project.properties["storePassword"] as String
        keyAlias = project.properties["keyAlias"] as String
        keyPassword = project.properties["keyPassword"] as String
      }
    }
  }

  buildTypes {
    release {
      signingConfig = if (project.hasProperty("storeFile")) {
        signingConfigs.getByName("release")
      } else {
        signingConfigs.getByName("debug")
      }
    }
  }
}

dependencies {
  // UIComponents
  api(libs.androidx.navigation.compose)
  api(libs.androidx.core.ktx)
  api(platform(libs.androidx.compose.bom))
  api(libs.androidx.compose.ui)
  api(libs.androidx.compose.ui.graphics)
  api(libs.androidx.compose.ui.tooling.preview)
  api(libs.androidx.compose.material3)

  // Hilt
  api(libs.hilt.navigation.compose)

  // Javax
  api(libs.javax.inject)

  // Tests
  testImplementation(libs.junit)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.compose.ui.test.junit4)
  debugImplementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.test.manifest)
}