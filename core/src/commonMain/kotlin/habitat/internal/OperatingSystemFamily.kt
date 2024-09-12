package habitat.internal

import habitat.Device
import habitat.OperatingSystem

internal fun String.toFamily(): OperatingSystem.Family = when {
    contains("darwin", ignoreCase = true) -> OperatingSystem.Family.MACOS
    contains("os x", ignoreCase = true) -> OperatingSystem.Family.MACOS
    contains("ios", ignoreCase = true) -> OperatingSystem.Family.IOS
    contains("mac", ignoreCase = true) -> OperatingSystem.Family.MACOS
    contains("win", ignoreCase = true) -> OperatingSystem.Family.WINDOWS
    contains("linux", ignoreCase = true) -> OperatingSystem.Family.LINUX
    contains("android", ignoreCase = true) -> OperatingSystem.Family.ANDROID
    else -> OperatingSystem.Family.UNKNOWN
}

internal fun OperatingSystem.toDeviceName() = when (family) {
    OperatingSystem.Family.MACOS -> "Mac"
    OperatingSystem.Family.IOS -> "iPhone"
    OperatingSystem.Family.WINDOWS -> "Windows"
    OperatingSystem.Family.LINUX -> "Linux"
    OperatingSystem.Family.ANDROID -> "Android"
    else -> "Unknown"
}

internal fun OperatingSystem.toManufacturer() = when (family) {
    OperatingSystem.Family.MACOS -> "Apple"
    OperatingSystem.Family.IOS -> "Apple"
    else -> "Unknown"
}

internal fun OperatingSystem.toDeviceType() = when (family) {
    OperatingSystem.Family.MACOS -> Device.Type.DESKTOP
    OperatingSystem.Family.IOS -> Device.Type.MOBILE
    OperatingSystem.Family.WINDOWS -> Device.Type.DESKTOP
    OperatingSystem.Family.LINUX -> Device.Type.DESKTOP
    OperatingSystem.Family.ANDROID -> Device.Type.MOBILE
    else -> Device.Type.UNKNOWN
}