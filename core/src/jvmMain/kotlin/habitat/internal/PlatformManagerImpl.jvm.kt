package habitat.internal

import habitat.Device
import habitat.JvmPlatform
import habitat.OperatingSystem
import habitat.Platform
import habitat.PlatformManager
import habitat.runtime.JvmRuntime

@PublishedApi
internal actual class PlatformManagerImpl actual constructor() : PlatformManager {
    actual override fun current(): Platform = JvmPlatform(
        host = run {
            val name = System.getProperty("os.name")
            OperatingSystem(
                name = name,
                family = name.toFamily(),
                version = System.getProperty("os.version")
            )
        },
        runtime = JvmRuntime(version = System.getProperty("java.version")),
        device = Device(
            name = "unknown",
            model = "unknown",
            cpu = System.getProperty("os.arch").toArch(),
            type = Device.Type.DESKTOP
        )
    )
}