package habitat.utils.common

fun <T : JsAny?> require(name: String): T = js("require(name)")