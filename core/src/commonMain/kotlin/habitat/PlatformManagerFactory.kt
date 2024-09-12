package habitat

import habitat.internal.PlatformManagerImpl
import kotlin.js.JsName

@JsName("createPlatformManager")
inline fun PlatformManager(): PlatformManager = PlatformManagerImpl()