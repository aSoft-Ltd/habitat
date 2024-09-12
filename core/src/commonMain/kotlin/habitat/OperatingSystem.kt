package habitat

import kotlinx.serialization.Serializable

@Serializable
data class OperatingSystem(
    val name: String,
    val family: Family,
    val version: String
) {
    companion object {
        val Unknown by lazy { OperatingSystem(name = "Unknown", family = Family.UNKNOWN, version = "Unknown") }
    }

    enum class Family {
        WINDOWS,
        MACOS,
        LINUX,
        IOS,
        ANDROID,
        UNKNOWN
    }
}