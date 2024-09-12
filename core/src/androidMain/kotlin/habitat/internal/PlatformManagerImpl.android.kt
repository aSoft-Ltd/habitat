package habitat.internal

import android.os.Build
import habitat.AndroidPlatform
import habitat.Device
import habitat.OperatingSystem
import habitat.Platform
import habitat.PlatformManager
import habitat.runtime.AndroidRuntime

@PublishedApi
internal actual class PlatformManagerImpl actual constructor() : PlatformManager {
    actual override fun current(): Platform = AndroidPlatform(
        host = run {
            val version = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Build.VERSION.RELEASE_OR_CODENAME
            } else {
                "?"
            }
            OperatingSystem(
                name = "Android $version",
                family = OperatingSystem.Family.ANDROID,
                version = version
            )
        },
        runtime = AndroidRuntime(
            sdk = Build.VERSION.SDK_INT,
            version = Build.VERSION.CODENAME ?: "?"
        ),
        device = Device(
            name = Build.DEVICE ?: "Unknown",
            model = Build.BRAND ?: "Unknown",
            cpu = System.getProperty("os.arch").toArch(),
            type = Device.Type.MOBILE
        )
    )
}