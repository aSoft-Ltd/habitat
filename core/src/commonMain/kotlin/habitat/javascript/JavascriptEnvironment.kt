package habitat.javascript

import kotlinx.serialization.Serializable

@Serializable
sealed interface JavascriptEnvironment {
    val version: String
    val name: String
}