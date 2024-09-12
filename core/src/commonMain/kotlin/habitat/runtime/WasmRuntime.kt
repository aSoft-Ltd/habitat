package habitat.runtime

import kotlinx.serialization.Serializable

@Serializable
data class WasmRuntime(
    override val version: String
) : Runtime