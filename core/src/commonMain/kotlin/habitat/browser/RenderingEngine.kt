package habitat.browser

import kotlinx.serialization.Serializable

@Serializable
enum class RenderingEngine {
    BLINK,
    GECKO,
    WEBKIT,
    UNKNOWN
}
