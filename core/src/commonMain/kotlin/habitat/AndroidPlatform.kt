package habitat

import habitat.runtime.AndroidRuntime
import habitat.runtime.JvmRuntime
import kotlinx.serialization.Serializable

@Serializable
data class AndroidPlatform(
    override val host: OperatingSystem,
    override val runtime: AndroidRuntime,
    override val device: Device
) : Platform {
    override val group by lazy { PlatformGroup.Jvm }

    override val version by lazy { "${host.name}-${device.name}" }
}