package habitat

import habitat.runtime.Runtime
import kotlinx.serialization.Serializable

@Serializable
sealed interface Platform {
    val group: PlatformGroup
    val host: OperatingSystem
    val runtime: Runtime
    val device: Device
    val version: String
}