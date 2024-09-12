package habitat.internal

import habitat.Device
import habitat.OperatingSystem
import habitat.Platform
import habitat.PlatformManager
import habitat.WasmPlatform
import habitat.runtime.WasmRuntime
import habitat.wasm.BrowserlessEnvironment

@PublishedApi
internal actual class PlatformManagerImpl actual constructor() : PlatformManager {
    actual override fun current(): Platform = WasmPlatform(
        environment = BrowserlessEnvironment(
            name = "wasi",
            version = "1.x"
        ),
        runtime = WasmRuntime(version = "1.x"),
        device = Device.Unknown,
        host = OperatingSystem.Unknown
    )
}