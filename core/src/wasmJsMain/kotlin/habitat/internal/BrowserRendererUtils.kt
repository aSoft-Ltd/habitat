package habitat.internal

import habitat.javascript.JavaScriptEngine
import habitat.browser.RenderingEngine
import habitat.browser.BrowserFamily
import habitat.javascript.JavascriptEnvironment

internal fun String.toRenderer(): RenderingEngine = when {
    contains("chrom", ignoreCase = true) -> RenderingEngine.BLINK
    contains("mozila", ignoreCase = true) -> RenderingEngine.GECKO
    contains("safari", ignoreCase = true) -> RenderingEngine.WEBKIT
    else -> RenderingEngine.UNKNOWN
}

internal fun String.toJavascriptEngine(): JavaScriptEngine = when {
    contains("chrom", ignoreCase = true) -> JavaScriptEngine.V8
    contains("mozila", ignoreCase = true) -> JavaScriptEngine.SPIDERMONKEY
    contains("safari", ignoreCase = true) -> JavaScriptEngine.JAVASCRIPTCORE
    else -> JavaScriptEngine.UNKNOWN
}

internal fun RenderingEngine.toFamily(): BrowserFamily = when (this) {
    RenderingEngine.BLINK -> BrowserFamily.Chromium
    RenderingEngine.GECKO -> BrowserFamily.Quantum
    RenderingEngine.WEBKIT -> BrowserFamily.Webkit
    RenderingEngine.UNKNOWN -> BrowserFamily.Unknown
}

internal fun JavascriptEnvironment.toEngine(): JavaScriptEngine = when {
    name.contains("node", ignoreCase = true) -> JavaScriptEngine.V8
    name.contains("deno", ignoreCase = true) -> JavaScriptEngine.V8
    name.contains("bun", ignoreCase = true) -> JavaScriptEngine.JAVASCRIPTCORE
    else -> JavaScriptEngine.UNKNOWN
}