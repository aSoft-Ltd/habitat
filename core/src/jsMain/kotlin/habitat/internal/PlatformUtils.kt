package habitat.internal

import habitat.utils.common.Function
import habitat.utils.common.UNDEFINED
import habitat.utils.common.navigator

private val browserCheckFunction = Function("try {return this===window;}catch(e){ return false;}")
private val nodeCheckFunction = Function("try {return this===global;}catch(e){return false;}")

internal val isRunningInBrowser: Boolean by lazy { browserCheckFunction.asDynamic()() }
internal val isRunningInNode: Boolean by lazy { nodeCheckFunction.asDynamic()() }
internal val isRunningInReactNative: Boolean by lazy {
    jsTypeOf(navigator) != UNDEFINED && navigator?.product == "ReactNative"
}

internal val isRunningInJsdom: Boolean by lazy {
    jsTypeOf(navigator) != UNDEFINED &&
            navigator != null &&
            navigator.userAgent != undefined &&
            jsTypeOf(navigator.userAgent) != UNDEFINED &&
            jsTypeOf(navigator.userAgent.match) != UNDEFINED &&
            navigator.userAgent.match("\\bjsdom\\b") == true
}

