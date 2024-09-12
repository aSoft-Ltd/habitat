package habitat.internal

import habitat.Platform
import habitat.PlatformManager

@PublishedApi
internal expect class PlatformManagerImpl() : PlatformManager {
    override fun current(): Platform
}