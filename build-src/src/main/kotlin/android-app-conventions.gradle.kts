import com.android.build.gradle.BaseExtension

plugins {
  id("com.android.application")
  kotlin("android")
}

configure<BaseExtension> {
  val libs = libsWorkaround

  plugins {
    id(libs.plugHiltAndroid())
    id(libs.plugKsp())
  }

  dependencies {
    implementation(libs.libHiltAndroid())
    kspWorkaround(libs.libHiltCompiler())
  }
}

android {
  namespace = AndroidConfig.APPLICATION_ID

  compileSdk = AndroidConfig.COMPILE_SDK

  sourceSets {
    named("main") {
      res.srcDirs(
        "src/main/res",
        "src/main/res/features/currencies",
        "src/main/res/features/favorites",
        "src/main/res/features/filters",
      )
    }
  }

  defaultConfig {
    applicationId = AndroidConfig.APPLICATION_ID
    minSdk = AndroidConfig.MIN_SDK
    targetSdk = AndroidConfig.TARGET_SDK
    versionCode = AndroidConfig.VERSION_CODE
    versionName = AndroidConfig.VERSION_NAME
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      isShrinkResources = true
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
      )
    }
    getByName("debug") {
      applicationIdSuffix = ".debug"
      isDebuggable = true
    }
  }

  compileOptions {
    sourceCompatibility = LangOptions.javaVersion
    targetCompatibility = LangOptions.javaVersion
  }

  buildFeatures {
    viewBinding = true
  }
}

kotlin {
  jvmToolchain(LangOptions.JVM_TOOLCHAIN)
}

dependencies {

  // Data
  implementation(projectsWorkaround.module.data)
  // Domain
  implementation(projectsWorkaround.module.domain)
  // Core
  implementation(projectsWorkaround.module.core)
}