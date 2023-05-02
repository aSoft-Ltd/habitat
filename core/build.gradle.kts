plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

android {
    configureAndroid("src/androidMain")
}

kotlin {
    android { library() }
    jvm { library() }
    js(IR) { library() }

    val targetGroups = mapOf(
        "macos" to macOsTargets(),
        "ios" to iosTargets(),
        "watchos" to watchOsTargets(),
        "tvos" to tvOsTargets(),
        "linux" to linuxTargets()
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
                implementation(projects.kommanderCore)
            }
        }

        val jsMain by getting {
            dependencies {
                api(npm("platform", npm.versions.platform.get()))
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


aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A Kotlin Multiplatform Library for detecting Platforms"
)