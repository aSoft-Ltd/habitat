package habitat.utils.npm

@JsModule("platform")
internal external val platform: Platform

internal external interface Platform : JsAny {
    val ua: String?
    val name: String
    val description: String
    val manufacturer: String?
    val version: String?
    val os: OS?
    val product: String
}

internal external interface OS : JsAny {
    val architecture: String
    val family: String
    val version: String?
}