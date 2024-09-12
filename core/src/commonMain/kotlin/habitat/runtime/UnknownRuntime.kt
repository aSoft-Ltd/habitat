package habitat.runtime

import kotlinx.serialization.Serializable

@Serializable
data object UnknownRuntime : Runtime {
    override val version: String = "unknown"
}