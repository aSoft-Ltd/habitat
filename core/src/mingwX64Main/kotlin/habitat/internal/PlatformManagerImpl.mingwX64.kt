package habitat.internal

import habitat.Architecture
import habitat.Device
import habitat.NativePlatform
import habitat.OperatingSystem
import habitat.Platform
import habitat.PlatformManager
import habitat.runtime.NativeRuntime

@PublishedApi
internal actual class PlatformManagerImpl actual constructor() : PlatformManager {
    actual override fun current(): Platform = NativePlatform(
        host = OperatingSystem(
            name = "Windows",
            family = OperatingSystem.Family.WINDOWS,
            version = "unknown"
        ),
        device = Device(
            name = "PC",
            model = "PC",
            cpu = Architecture.X64,
            type = Device.Type.DESKTOP
        ),
        runtime = NativeRuntime("unknown")
    )
}