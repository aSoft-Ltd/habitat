package habitat

import habitat.runtime.NativeRuntime
import kotlinx.serialization.Serializable

@Serializable
data class NativePlatform(
    override val group: PlatformGroup,
    override val host: OperatingSystem,
    override val runtime: NativeRuntime,
    override val device: Device
) : Platform {
    override val version by lazy {
        "${host.name}-${host.version}"
    }
}