package habitat

import habitat.runtime.Runtime
import kotlinx.serialization.Serializable

@Serializable
data class UnknownPlatform(
    override val runtime: Runtime,
    override val group: PlatformGroup,
    override val host: OperatingSystem,
    override val device: Device,
) : Platform {
    override val version by lazy {
        "${host.name}-${host.family}-${host.version}-${device.type}"
    }
}