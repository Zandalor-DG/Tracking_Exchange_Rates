plugins {
  alias(libs.plugins.convention.kotlin.library)
}

dependencies {
  // Kotlin
  api(libs.kotlinx.coroutines.core)

  // Javax
  api(libs.javax.inject)
}