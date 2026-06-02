package habitat.node

external interface Process : JsAny {
    val platform: String
    val version: String

    val arch: String
    val versions: Versions
}

external interface Versions : JsAny {
    val v8: String
}

external val process: Process