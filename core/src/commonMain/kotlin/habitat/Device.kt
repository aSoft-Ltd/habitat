package habitat

import kotlinx.serialization.Serializable

@Serializable
data class Device(
    val name: String,
    val model: String,
    val cpu: Architecture,
    val type: Type
) {
    companion object {
        val Unknown by lazy { Device("Unknown", "Unknown", Architecture.UNKNOWN, Type.UNKNOWN) }
    }

    enum class Type {
        DESKTOP,
        MOBILE,
        TABLET,
        TV,
        WEARABLE,
        EMBEDDED,
        UNKNOWN
    }
}