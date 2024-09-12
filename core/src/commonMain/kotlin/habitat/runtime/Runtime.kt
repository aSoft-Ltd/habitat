package habitat.runtime

import kotlinx.serialization.Serializable

@Serializable
sealed interface Runtime {
    val version: String
}