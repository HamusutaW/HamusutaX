@file:Suppress("unused")
package hamusutax.okhttp

import okhttp3.HttpUrl
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun buildHttpUrl(builderAction: HttpUrl.Builder.() -> Unit): HttpUrl {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return HttpUrl.Builder().apply(builderAction).build()
}

fun HttpUrl.Builder.addQueryParameters(params: Map<String, String?>): HttpUrl.Builder {
    params.forEach { (key, value) ->
        addQueryParameter(key, value)
    }
    return this
}
