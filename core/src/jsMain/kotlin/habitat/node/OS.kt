package habitat.node

@JsModule("os")
@JsNonModule
external object OS {
    fun platform(): String
    fun release(): String
}