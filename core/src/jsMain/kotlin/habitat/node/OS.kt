package habitat.node

@JsModule("os")
@JsNonModule
@JsName("os")
internal external val operatingSystem: OS

external interface OS {
    fun platform(): String
    fun release(): String
}