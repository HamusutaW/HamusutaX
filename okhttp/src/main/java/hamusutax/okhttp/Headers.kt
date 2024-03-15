@file:Suppress("unused")
package hamusutax.okhttp

import okhttp3.Headers
import okhttp3.Headers.Companion.toHeaders
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

private val EmptyHeaders = emptyMap<String, String>().toHeaders()

fun emptyHeaders() = EmptyHeaders

inline fun buildHeaders(builderAction: Headers.Builder.() -> Unit): Headers {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return Headers.Builder().apply(builderAction).build()
}
