package habitat.internal

import habitat.Device
import habitat.JvmPlatform
import habitat.OperatingSystem
import habitat.Platform
import habitat.PlatformManager
import habitat.runtime.JvmRuntime
import java.net.InetAddress


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
            name = getComputerName() ?: "Unknown",
            model = "Unknown",
            cpu = System.getProperty("os.arch").toArch(),
            type = Device.Type.DESKTOP
        )
    )

    //TODO: Find a way to test this on linux and windows as well
    private fun getComputerName(): String? = try {
        InetAddress.getLocalHost().getHostName()?.substringBefore(".")
    } catch (_: Throwable) {
        null
    }
}