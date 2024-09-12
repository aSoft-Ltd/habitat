package habitat.runtime

import kotlinx.serialization.Serializable

@Serializable
data class JvmRuntime(
    override val version: String
) : Runtime