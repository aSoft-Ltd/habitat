package habitat.internal

import habitat.Architecture
import habitat.Device
import habitat.NativePlatform
import habitat.OperatingSystem
import habitat.Platform
import habitat.PlatformManager
import habitat.runtime.NativeRuntime
import platform.UIKit.UIDevice

@PublishedApi
internal actual class PlatformManagerImpl actual constructor() : PlatformManager {
    actual override fun current(): Platform = NativePlatform(
        host = OperatingSystem(
            name = UIDevice.currentDevice.systemName,
            family = OperatingSystem.Family.TVOS,
            version = UIDevice.currentDevice.systemVersion
        ),
        runtime = NativeRuntime(
            version = UIDevice.currentDevice.systemVersion
        ),
        device = Device(
            name = UIDevice.currentDevice.name,
            model = UIDevice.currentDevice.model,
            cpu = Architecture.X64,
            type = Device.Type.TV
        )
    )
}