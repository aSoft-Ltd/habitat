package habitat.runtime

import habitat.javascript.JavaScriptEngine
import kotlinx.serialization.Serializable

@Serializable
data class JavascriptRuntime(
    val engine: JavaScriptEngine,
    override val version: String
) : Runtime