@file:Suppress("unused")
package hamusutax.okhttp.method

import hamusutax.okhttp.addQueryParameters
import hamusutax.okhttp.buildFormBody
import hamusutax.okhttp.buildRequest
import hamusutax.okhttp.emptyFormBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import okhttp3.Headers.Companion.toHeaders
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * @param data [String] 或 [Map]
 * @param json [String]、[JsonElement] 或其它可被 [Json.encodeToString] 序列化的对象
 */
suspend inline fun <reified T> OkHttpClient.post(
    url: String,
    data: Any? = null,
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
        data = when {
            data != null -> when (data) {
                is String -> data.toRequestBody()
                is Map<*, *> -> {
                    buildFormBody {
                        data.forEach { (key, value) ->
                            add(key.toString(), value.toString())
                        }
                    }
                }
                else -> throw IllegalArgumentException("Request data type is String or Map!")
            }
            json != null -> when (json) {
                is String -> json
                else -> jsonParser.encodeToString<T>(json)
            }.toRequestBody("application/json;charset=utf-8".toMediaType())
            else -> emptyFormBody()
        },
        cookies = cookies
    )

suspend fun OkHttpClient.post(
    url: String,
    data: RequestBody = emptyFormBody(),
    params: Map<String, Any?> = emptyMap(),
    headers: Map<String, Any> = emptyMap(),
    cookies: Map<String, Any> = emptyMap()
) =
    withContext(Dispatchers.IO) {
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
        newCall(request).execute()
    }
