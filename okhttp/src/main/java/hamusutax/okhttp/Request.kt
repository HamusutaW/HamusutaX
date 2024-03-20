@file:Suppress("unused")
package hamusutax.okhttp

import okhttp3.Request
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun buildRequest(builderAction: Request.Builder.() -> Unit): Request {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return Request.Builder().apply(builderAction).build()
}
