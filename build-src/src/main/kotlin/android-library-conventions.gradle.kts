import com.android.build.gradle.BaseExtension

plugins {
  id("com.android.library")
  kotlin("android")
}

configure<BaseExtension> {
  val libs = libsWorkaround

  plugins {
    id(libs.plugKotlinxSerialization())
    id(libs.plugHiltAndroid())
    id(libs.plugKsp())
  }

  dependencies {
    implementation(libs.libKotlinXSerialization())
    implementation(libs.libHiltAndroid())
    kspWorkaround(libs.libHiltCompiler())
    implementation(libs.libHiltExtensions())
    kspWorkaround(libs.libHiltExtensionsProcessor())
  }
}

android {
  namespace = AndroidConfig.APPLICATION_ID + ".${project.name}"

  compileSdk = AndroidConfig.COMPILE_SDK

  defaultConfig {
    minSdk = AndroidConfig.MIN_SDK
  }

  compileOptions {
    sourceCompatibility = LangOptions.javaVersion
    targetCompatibility = LangOptions.javaVersion
  }

  buildFeatures {
    buildConfig = true
  }
}

kotlin {
  jvmToolchain(jdkVersion = LangOptions.JVM_TOOLCHAIN)
}

dependencies {
  // Domain
  implementation(projectsWorkaround.module.domain)
}