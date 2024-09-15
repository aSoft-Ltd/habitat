@file:OptIn(ExperimentalForeignApi::class)

package habitat.internal

import habitat.Architecture
import habitat.Device
import habitat.NativePlatform
import habitat.OperatingSystem
import habitat.Platform
import habitat.PlatformGroup
import habitat.PlatformManager
import habitat.runtime.NativeRuntime
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toKString
import platform.posix.uname
import platform.posix.utsname

@PublishedApi
internal actual class PlatformManagerImpl actual constructor() : PlatformManager {
    actual override fun current(): Platform {
        val s = memScoped {
            val sys = alloc<utsname>()
            uname(sys.ptr)
            sys
        }
        return NativePlatform(
            group = PlatformGroup.Linux,
            host = OperatingSystem(
                name = s.sysname.toKString(),
                family = OperatingSystem.Family.LINUX,
                version = s.version.toKString()
            ),
            runtime = NativeRuntime(
                version = s.version.toKString()
            ),
            device = Device(
                name = s.machine.toKString(),
                model = s.release.toKString(),
                cpu = Architecture.X64,
                type = Device.Type.DESKTOP
            )
        )
    }
}