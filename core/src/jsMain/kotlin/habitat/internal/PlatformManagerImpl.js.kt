package habitat.internal

import habitat.Device
import habitat.JavascriptPlatform
import habitat.OperatingSystem
import habitat.Platform
import habitat.PlatformManager
import habitat.javascript.BrowserEnvironment
import habitat.javascript.BrowserlessEnvironment
import habitat.node.OS
import habitat.node.process
import habitat.runtime.JavascriptRuntime
import habitat.utils.common.require

@PublishedApi
internal actual class PlatformManagerImpl actual constructor() : PlatformManager {
    actual override fun current(): Platform {
        val p = require<habitat.utils.npm.Platform?>("platform")
        var host = run {
            val name = p?.os?.family ?: "unknown"
            OperatingSystem(
                name = name,
                family = name.toFamily(),
                version = p?.os?.version ?: "unknown"
            )
        }

        val device = Device(
            name = p?.manufacturer ?: host.toDeviceName(),
            model = p?.manufacturer ?: host.toManufacturer(),
            cpu = "${p?.os?.architecture}".toArch(),
            type = host.toDeviceType()
        )
        if (isRunningInBrowser) {
            val environment = run {
                val name = p?.name ?: "Unknown"
                val engine = name.toRenderer()
                BrowserEnvironment(
                    name = name,
                    engine = engine,
                    family = engine.toFamily(),
                    version = p?.version ?: "unknown"
                )
            }
            return JavascriptPlatform(
                environment = environment,
                runtime = JavascriptRuntime(engine = environment.name.toJavascriptEngine(), version = p?.version ?: "unknown"),
                device = device,
                host = host
            )
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
        val runtime = JavascriptRuntime(
            engine = environment.toEngine(),
            version = process.version
        )
        return JavascriptPlatform(environment, runtime, device, host)
    }
}