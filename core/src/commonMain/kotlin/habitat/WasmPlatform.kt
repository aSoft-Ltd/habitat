package habitat

import habitat.runtime.WasmRuntime
import habitat.wasm.WasmEnvironment
import kotlinx.serialization.Serializable

@Serializable
data class WasmPlatform(
    val environment: WasmEnvironment,
    override val runtime: WasmRuntime,
    override val device: Device,
    override val host: OperatingSystem
) : Platform {
    override val group by lazy { PlatformGroup.Javascript }

    override val version by lazy {
        // chromium-mobile-234.0.0-windows-10.0-Unknown
        "${environment.name}-${device.type}-${environment.version}-${host.family}-${host.version}"
    }
}