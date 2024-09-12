import habitat.Platform
import habitat.PlatformManager
import kotlin.test.Test
import kotlin.test.fail
import kotlinx.serialization.json.Json

class PlatformTest {

    @Test
    fun should_detect_the_platform_type() {
        val manager = PlatformManager()
        val platform = manager.current()
        println("Platform: $platform")
        val codec = Json { prettyPrint = true }
        val json = codec.encodeToString(Platform.serializer(), platform)
        println(json)
    }
}