import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

description = "A kotlin multiplatform library to detect the current running platform"

configureAndroid("src/androidMain") {
    namespace = "tz.co.asoft.habitat"
    defaultConfig {
        minSdk = 14
    }

    lintOptions {
        isAbortOnError = false
    }
}

kotlin {
    if (Targeting.ANDROID) androidTarget { library() }
    if (Targeting.JVM) jvm { library() }
    if (Targeting.JS) js(IR) { library() }
    if (Targeting.WASM) wasmJs { library() }
    if (Targeting.WASM) wasmWasi { library() }
    val osxTargets = if (Targeting.OSX) osxTargets() else listOf()
    val ndkTargets = if (Targeting.NDK) ndkTargets() else listOf()
    val linuxTargets = if (Targeting.LINUX) linuxTargets() else listOf()
    val mingwTargets = if (Targeting.MINGW) mingwTargets() else listOf()

    sourceSets {
        commonMain.dependencies {
            api(kotlinx.serialization.core)
        }

        commonTest.dependencies {
            implementation(kotlinx.serialization.json)
            implementation(libs.kommander.core)
        }

        jsMain.dependencies {
            implementation(npm("platform", file("src/jsMain/resources/platform")))
        }

        wasmJsMain.dependencies {
            implementation(npm("platform", file("src/jsMain/resources/platform")))
        }
    }
}

rootProject.the<NodeJsRootExtension>().apply {
    version = npm.versions.node.version.get()
    downloadBaseUrl = npm.versions.node.url.get()
}

//rootProject.tasks.withType<KotlinNpmInstallTask>().configureEach {
//    args.add("--ignore-engines")
//}

tasks.named("wasmJsTestTestDevelopmentExecutableCompileSync").configure {
    mustRunAfter(tasks.named("jsBrowserTest"))
    mustRunAfter(tasks.named("jsNodeTest"))
}