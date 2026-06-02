package habitat.node

external interface Process {
    val platform: String
    val version: String
    val versions: Versions
    val arch: String
}

external interface Versions {
    val v8: String
}

external val process: Process