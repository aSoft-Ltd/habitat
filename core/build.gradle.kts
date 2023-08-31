plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

description = "A Kotlin Multiplatform Library for detecting Platforms"

android {
    configureAndroid("src/androidMain")
}

kotlin {
    android { library() }
    jvm { library() }
    if(Targeting.JS) js(IR) { library() }

    val targetGroups = mapOf(
        "macos" to if(Targeting.OSX) macOsTargets() else listOf(),
        "ios" to if(Targeting.OSX) iosTargets() else listOf(),
        "watchos" to if(Targeting.OSX) watchOsTargets() else listOf(),
        "tvos" to if(Targeting.OSX) tvOsTargets() else listOf(),
        "linux" to if(Targeting.LINUX) linuxTargets() else listOf()
    )

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlinx.serialization.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlinx.serialization.json)
                implementation(libs.kommander.core)
            }
        }

        if(Targeting.JS) {
            val jsMain by getting {
                dependencies {
                    api(npm("platform", npm.versions.platform.get()))
                }
            }
        }

        for ((platform, targets) in targetGroups) {
            val intermediate = create("${platform}Main") {
                dependsOn(commonMain)
            }
            for (target in targets) {
                val main by target.compilations
                main.defaultSourceSet.dependsOn(intermediate)
            }
        }
    }
}