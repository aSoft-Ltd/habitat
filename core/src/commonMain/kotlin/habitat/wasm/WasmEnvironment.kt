package habitat.wasm

import kotlinx.serialization.Serializable

@Serializable
sealed interface WasmEnvironment {
    val version: String
    val name: String
}