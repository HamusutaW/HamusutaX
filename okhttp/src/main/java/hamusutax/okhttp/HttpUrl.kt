@file:Suppress("unused")
package hamusutax.okhttp

import okhttp3.HttpUrl

fun HttpUrl.Builder.addQueryParameters(params: Map<String, String?>): HttpUrl.Builder {
    params.forEach { (key, value) ->
        addQueryParameter(key, value)
    }
    return this
}
