package habitat

import habitat.runtime.NativeRuntime
import kotlinx.serialization.Serializable

@Serializable
data class NativePlatform(
    override val host: OperatingSystem,
    override val runtime: NativeRuntime,
    override val device: Device
) : Platform {
    override val group by lazy { PlatformGroup.Jvm }

    override val version by lazy {
        "${host.name}-${host.version}"
    }
}