# habitat

Be aware of the platform you are currently running in.

## What is habitat?
Habitat is a Kotlin Multiplatform library that provides a unified API to detect and inspect the platform your code is currently running on. It offers detailed information about the operating system, runtime environment, device type, and hardware architecture across a wide range of platforms.

## Quick Setup
Add the dependency to your `build.gradle.kts` file:

```kotlin
dependencies {
    implementation("tz.co.asoft:habitat-core:2.0.18")
}
```

## Usage
To get started, create an instance of `PlatformManager` and call `current()` to get the current platform information:

```kotlin
val manager = PlatformManager()
val platform = manager.current()

// 1. Get the operating system name & version
println("OS: ${platform.host.name} (${platform.host.version})")

// 2. Get the execution environment name & version
println("Environment: ${platform.runtime.version}")
if (platform is JavascriptPlatform) {
    println("Engine: ${platform.runtime.engine}")
} else if (platform is JvmPlatform) {
    println("JVM Version: ${platform.runtime.version}")
}

// 3. Get device information
val device = platform.device
println("Device: ${device.name} (${device.model})")
println("Type: ${device.type}")
println("Architecture: ${device.cpu}")
```

## Detailed Information
You can query several properties from the `Platform` object to get fine-grained details:

### `platform.host` (Operating System)
Returns an `OperatingSystem` object containing:
- `name`: Human-readable name (e.g., "Android 13", "Windows 10").
- `family`: Enum value (e.g., `WINDOWS`, `MACOS`, `LINUX`, `ANDROID`, `IOS`).
- `version`: The OS version string.

### `platform.device`
Returns a `Device` object containing:
- `name`: Device name or manufacturer.
- `model`: Specific model identifier.
- `type`: Enum value (e.g., `DESKTOP`, `MOBILE`, `TABLET`, `TV`, `WEARABLE`).
- `cpu`: The hardware architecture (see below).

### `platform.device.cpu` (Hardware Architecture)
Returns an `Architecture` enum value such as:
- `X86`, `X64`
- `ARM`, `ARM64`
- `MIPS`, `PPC`, `SPARC`
- `UNKNOWN`

### `platform.runtime`
Provides information about the execution environment:
- `version`: Version of the runtime (JVM version, Node.js version, etc.).
- **Specific Runtimes**:
    - `JavascriptRuntime`: Includes `engine` (V8, SPIDERMONKEY, etc.).
    - `AndroidRuntime`: Includes `sdk` (API level).

## Available platforms
Habitat supports a broad set of platforms:

- **JVM**: Standard Java Virtual Machine.
- **Android**: Android applications and Android Native.
- **JavaScript**: Browser, Node.js, Deno, and Bun.
- **Wasm**: WebAssembly environments.
- **Native**: 
    - **MacOS** (X64, Arm64)
    - **iOS** (Arm64, Simulators)
    - **WatchOS**
    - **TvOS**
    - **Linux** (X64, Arm64)
    - **Windows** (X64)
