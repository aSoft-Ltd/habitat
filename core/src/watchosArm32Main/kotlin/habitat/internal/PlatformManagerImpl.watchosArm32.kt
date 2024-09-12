package habitat.internal

import habitat.Architecture
import habitat.Device
import habitat.NativePlatform
import habitat.OperatingSystem
import habitat.Platform
import habitat.PlatformManager
import habitat.runtime.NativeRuntime
import platform.Foundation.NSProcessInfo

@PublishedApi
internal actual class PlatformManagerImpl actual constructor() : PlatformManager {
    actual override fun current(): Platform {
        val version = NSProcessInfo.processInfo.operatingSystemVersionString.substringAfter(" ").substringBefore(" ")
        return NativePlatform(
            host = OperatingSystem(
                name = "WatchOs",
                family = OperatingSystem.Family.WATCHOS,
                version = version
            ),
            runtime = NativeRuntime(version),
            device = Device(
                name = "Apple Watch",
                model = "Apple Watch",
                cpu = Architecture.ARM,
                type = Device.Type.WEARABLE
            )
        )
    }
}