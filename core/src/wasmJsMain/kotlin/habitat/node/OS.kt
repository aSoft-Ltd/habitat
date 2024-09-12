package habitat.node

external interface OS : JsAny {
    fun platform(): String
    fun release(): String
}