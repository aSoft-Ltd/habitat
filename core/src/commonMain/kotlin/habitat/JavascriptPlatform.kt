package habitat

import habitat.javascript.JavascriptEnvironment
import habitat.runtime.JavascriptRuntime
import kotlinx.serialization.Serializable

@Serializable
data class JavascriptPlatform(
    val environment: JavascriptEnvironment,
    override val runtime: JavascriptRuntime,
    override val device: Device,
    override val host: OperatingSystem
) : Platform {
    override val group by lazy { PlatformGroup.Javascript }

    override val version by lazy {
        // chromium-mobile-234.0.0-windows-10.0-Unknown
        "${environment.name}-${device.type}-${environment.version}-${host.family}-${host.version}"
    }
}