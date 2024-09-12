package habitat

import habitat.runtime.JvmRuntime
import kotlinx.serialization.Serializable

@Serializable
data class JvmPlatform(
    override val host: OperatingSystem,
    override val runtime: JvmRuntime,
    override val device: Device
) : Platform {
    override val group by lazy { PlatformGroup.Jvm }

    override val version by lazy {
        // jvm-windows-10.0
        "jvm-${host.family}-${host.version}"
    }
}