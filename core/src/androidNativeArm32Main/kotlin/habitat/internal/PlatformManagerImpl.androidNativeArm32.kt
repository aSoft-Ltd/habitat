@file:OptIn(ExperimentalForeignApi::class)

package habitat.internal

import habitat.Architecture
import habitat.Device
import habitat.NativePlatform
import habitat.OperatingSystem
import habitat.Platform
import habitat.PlatformManager
import habitat.runtime.NativeRuntime
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.__system_property_get
import platform.posix.android_get_device_api_level

@PublishedApi
internal actual class PlatformManagerImpl actual constructor() : PlatformManager {
    actual override fun current(): Platform = NativePlatform(
        host = run {
            val version = memScoped {
                val buf = allocArray<ByteVar>(555)
                __system_property_get("ro.build.version.release", buf)
                buf.toKString()
            }
            OperatingSystem(
                name = "Android $version",
                family = OperatingSystem.Family.ANDROID,
                version = version
            )
        },
        runtime = NativeRuntime(
            version = "${android_get_device_api_level()}"
        ),
        device = Device(
            name = "unknown",
            model = "unknown",
            cpu = Architecture.ARM,
            type = Device.Type.MOBILE
        )
    )
}