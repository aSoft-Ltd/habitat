package habitat.runtime

import kotlinx.serialization.Serializable

@Serializable
data class NativeRuntime(
    override val version: String
) : Runtime