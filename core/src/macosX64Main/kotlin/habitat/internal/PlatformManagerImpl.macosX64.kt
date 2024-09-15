package habitat.internal

import habitat.Architecture
import habitat.Device
import habitat.NativePlatform
import habitat.OperatingSystem
import habitat.Platform
import habitat.PlatformGroup
import habitat.PlatformManager
import habitat.runtime.NativeRuntime
import platform.Foundation.NSProcessInfo

@PublishedApi
internal actual class PlatformManagerImpl actual constructor() : PlatformManager {
    actual override fun current(): Platform {
        val version = NSProcessInfo.processInfo.operatingSystemVersionString.substringAfter(" ").substringBefore(" ")
        return NativePlatform(
            group = PlatformGroup.MacOS,
            host = OperatingSystem(
                name = "Macos",
                family = OperatingSystem.Family.MACOS,
                version = version
            ),
            runtime = NativeRuntime(version),
            device = Device(
                name = "Mac",
                model = "Macos",
                cpu = Architecture.X64,
                type = Device.Type.DESKTOP
            )
        )
    }
}