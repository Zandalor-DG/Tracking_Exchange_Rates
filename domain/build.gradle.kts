plugins {
  alias(libs.plugins.convention.kotlin.library)
}

dependencies {
  // Core
  api(projects.core)

  // Kotlin
  api(libs.kotlinx.coroutines.core)

  // Javax
  api(libs.javax.inject)
}