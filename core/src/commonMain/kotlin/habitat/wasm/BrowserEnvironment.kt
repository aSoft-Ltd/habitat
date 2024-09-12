package habitat.wasm

import habitat.browser.BrowserFamily
import habitat.browser.RenderingEngine
import kotlinx.serialization.Serializable

@Serializable
data class BrowserEnvironment(
    override val name: String,
    val engine: RenderingEngine,
    val family: BrowserFamily,
    override val version: String,
) : WasmEnvironment