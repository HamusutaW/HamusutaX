@file:Suppress("unused")
package hamusutax.okhttp.method

import hamusutax.okhttp.addQueryParameters
import hamusutax.okhttp.buildRequest
import okhttp3.Headers.Companion.toHeaders
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Response

fun OkHttpClient.get(
    url: String,
    params: Map<String, Any?> = emptyMap(),
    headers: Map<String, Any> = emptyMap(),
    cookies: Map<String, Any> = emptyMap()
): Response {
    val httpUrl = url.toHttpUrl().newBuilder()
        .addQueryParameters(params.mapValues { it.value?.toString() })
        .build()
    val finalHeaders = headers.mapValues { it.value.toString() }.toMutableMap()
    if (cookies.isNotEmpty())
        finalHeaders["Cookie"] = cookies.map { "${it.key}=${it.value}" }.joinToString("; ")
    val request = buildRequest {
        url(httpUrl)
        headers(finalHeaders.toHeaders())
    }
    return newCall(request).execute()
}
