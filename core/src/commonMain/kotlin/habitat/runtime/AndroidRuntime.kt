package habitat.runtime

import kotlinx.serialization.Serializable

@Serializable
data class AndroidRuntime(
    val sdk: Int,
    override val version: String
) : Runtime