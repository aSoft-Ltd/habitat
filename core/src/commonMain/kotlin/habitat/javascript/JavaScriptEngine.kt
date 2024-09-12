package habitat.javascript

import kotlinx.serialization.Serializable

@Serializable
enum class JavaScriptEngine {
    V8,
    SPIDERMONKEY,
    JAVASCRIPTCORE,
    UNKNOWN
}