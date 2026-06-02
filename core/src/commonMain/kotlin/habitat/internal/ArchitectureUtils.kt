package habitat.internal

import habitat.Architecture

internal fun String?.toArch(): Architecture = when {
    this == null -> Architecture.UNKNOWN
    contains("arm", ignoreCase = true) && contains("64") -> Architecture.ARM64
    contains("arch", ignoreCase = true) && contains("64") -> Architecture.ARM64
    contains("apple", ignoreCase = true) && contains("silicon", ignoreCase = true) -> Architecture.ARM64
    contains("mac", ignoreCase = true) && contains("intel", ignoreCase = true) -> Architecture.X64
    contains("arm", ignoreCase = true) -> Architecture.ARM
    contains("amd", ignoreCase = true) && contains("64", ignoreCase = true) -> Architecture.X64
    contains("amd", ignoreCase = true) -> Architecture.X86
    contains("64", ignoreCase = true) -> Architecture.X64
    contains("86", ignoreCase = true) -> Architecture.X86
    else -> Architecture.UNKNOWN
}