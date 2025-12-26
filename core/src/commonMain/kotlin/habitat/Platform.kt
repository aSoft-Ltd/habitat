package habitat

import habitat.runtime.Runtime
import habitat.runtime.UnknownRuntime
import kotlinx.serialization.Serializable

@Serializable
sealed interface Platform {
    val group: PlatformGroup
    val host: OperatingSystem
    val runtime: Runtime
    val device: Device
    val version: String
    val name get() = group.name

    companion object {
        val Unknown by lazy {
            UnknownPlatform(
                runtime = UnknownRuntime,
                group = PlatformGroup.Unknown,
                host = OperatingSystem.Unknown,
                device = Device.Unknown
            )
        }
    }
}