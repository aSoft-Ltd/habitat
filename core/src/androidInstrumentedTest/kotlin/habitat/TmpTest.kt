package habitat

import kotlinx.serialization.json.Json
import org.junit.Ignore
import org.junit.Test

@Ignore("Not sure if the CI server has the right emulator to run this")
class TmpTest {
    @Test
    fun should_detect_the_platform_type() {
        val manager = PlatformManager()
        val platform = manager.current()
        println("Platform: $platform")
        val codec = Json {
            prettyPrint = true
        }
        val json = codec.encodeToString(Platform.serializer(), platform)
        println(json)
    }
}