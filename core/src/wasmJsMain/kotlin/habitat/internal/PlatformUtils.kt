package habitat.internal

internal fun browserCheckFunction(): JsBoolean = js("(new Function('try {return this===window;}catch(e){ return false;}'))()")

internal fun nodeCheckFunction(): JsBoolean = js("(new Function('try {return this===global;}catch(e){return false;}'))()")
//private val browserCheckFunction = Function("try {return this===window;}catch(e){ return false;}")
//private val nodeCheckFunction = Function("try {return this===global;}catch(e){return false;}")

internal val isRunningInBrowser: Boolean by lazy { browserCheckFunction().toBoolean() }
internal val isRunningInNode: Boolean by lazy { nodeCheckFunction().toBoolean() }