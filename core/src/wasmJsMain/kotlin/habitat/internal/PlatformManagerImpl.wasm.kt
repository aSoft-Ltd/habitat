package habitat.internal

import habitat.Device
import habitat.OperatingSystem
import habitat.Platform
import habitat.PlatformManager
import habitat.WasmPlatform
import habitat.node.OS
import habitat.node.process
import habitat.runtime.WasmRuntime
import habitat.utils.common.require
import habitat.utils.npm.platform
import habitat.wasm.BrowserEnvironment
import habitat.wasm.BrowserlessEnvironment

@PublishedApi
internal actual class PlatformManagerImpl actual constructor() : PlatformManager {
    override fun current(): Platform {
        val p = platform
        var host = run {
            val name = p.os?.family ?: "unknown"
            OperatingSystem(
                name = name,
                family = name.toFamily(),
                version = p.os?.version ?: "unknown"
            )
        }

        val device = Device(
            name = p.manufacturer ?: host.toDeviceName(),
            model = p.manufacturer ?: host.toManufacturer(),
            cpu = "${p.os?.architecture}".toArch(),
            type = host.toDeviceType()
        )

        val runtime = WasmRuntime(version = "1.x")
        println("Before browser check")
        if (isRunningInBrowser) {
            val environment = run {
                val name = p.name
                val engine = name.toRenderer()
                BrowserEnvironment(
                    name = name,
                    engine = engine,
                    family = engine.toFamily(),
                    version = p?.version ?: "unknown"
                )
            }
            return WasmPlatform(environment, runtime, device, host)
        }


        val os = require<OS?>("os")
        host = run {
            val name = os?.platform() ?: process.platform
            OperatingSystem(
                name = name,
                family = name.toFamily(),
                version = os?.release() ?: "Unknown"
            )
        }
        val environment = BrowserlessEnvironment(
            name = "Node",
            version = process.version
        )
        return WasmPlatform(environment, runtime, device, host)
    }
}