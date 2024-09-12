package habitat.browser

import kotlinx.serialization.Serializable

@Serializable
enum class BrowserFamily {
    Chromium, Webkit, Quantum, Unknown
}