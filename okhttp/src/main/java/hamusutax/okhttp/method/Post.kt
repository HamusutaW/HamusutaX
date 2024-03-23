@file:Suppress("unused")
package hamusutax.okhttp.method

import hamusutax.okhttp.addQueryParameters
import hamusutax.okhttp.buildFormBody
import hamusutax.okhttp.buildRequest
import hamusutax.okhttp.emptyFormBody
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import okhttp3.Headers.Companion.toHeaders
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response

/**
 * @param data 未进行 URL 编码的 [String] 或 [Map]
 */
fun OkHttpClient.post(
    url: String,
    data: Any? = null,
    params: Map<String, Any?> = emptyMap(),
    headers: Map<String, Any> = emptyMap(),
    cookies: Map<String, Any> = emptyMap()
) =
    post(
        url = url,
        params = params,
        headers = headers,
        data = when (data) {
            is String -> data.toRequestBody()
            is Map<*, *> -> {
                buildFormBody {
                    data.forEach { (key, value) ->
                        add(key.toString(), value.toString())
                    }
                }
            }
            else -> throw IllegalArgumentException("Request data type is String or Map!")
        },
        cookies = cookies
    )

/**
 * @param json [String]、[JsonElement] 或其它可被 [Json.encodeToString] 序列化的对象
 */
inline fun <reified T> OkHttpClient.post(
    url: String,
    json: T? = null,
    params: Map<String, Any?> = emptyMap(),
    headers: Map<String, Any> = emptyMap(),
    cookies: Map<String, Any> = emptyMap(),
    jsonParser: Json = Json
) =
    post(
        url = url,
        params = params,
        headers = headers,
        data = when (json) {
            null -> emptyFormBody()
            is String -> json.toRequestBody("application/json;charset=utf-8".toMediaType())
            else -> jsonParser.encodeToString<T>(json)
                .toRequestBody("application/json;charset=utf-8".toMediaType())
        },
        cookies = cookies
    )

fun OkHttpClient.post(
    url: String,
    data: RequestBody = emptyFormBody(),
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
        post(data)
    }
    return newCall(request).execute()
}
