package habitat.node

external interface OS {
    fun platform(): String
    fun release(): String
}