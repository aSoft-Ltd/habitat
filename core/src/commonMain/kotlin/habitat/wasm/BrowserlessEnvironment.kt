package habitat.wasm

import kotlinx.serialization.Serializable

@Serializable
data class BrowserlessEnvironment(
    override val name: String,
    override val version: String,
) : WasmEnvironment