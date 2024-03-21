@file:Suppress("unused")
package hamusutax.okhttp

import hamusutax.serialization.decodeSerializableValue
import hamusutax.serialization.encodeSerializableValue
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * 存储于内存中的 CookieJar
 *
 * 可使用 [kotlinx.serialization] 进行持久化
 */
@Serializable(MemoryCookieJarSerializer::class)
open class MemoryCookieJar internal constructor(
    cookies: Map<String, List<Cookie>> = emptyMap()
): CookieJar {
    val cookieStore = cookies.toMutableMap()
    override fun loadForRequest(url: HttpUrl) =
        (cookieStore[url.host] ?: emptyList()).filter {
            it.expiresAt > System.currentTimeMillis() && it.matches(url)
        }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieStore[url.host] =  ((cookieStore[url.host] ?: emptyList()) + cookies)
            .filter { it.expiresAt > System.currentTimeMillis() }
            .reversed() // 两次翻转以保留靠后的 Cookies
            .distinctBy { Triple(it.domain, it.name, it.path) }
            .reversed()
    }

    operator fun get(url: HttpUrl) = loadForRequest(url)

    operator fun set(url: HttpUrl, cookies: List<Cookie>) = saveFromResponse(url, cookies)

    fun clear() = cookieStore.clear()

    override fun toString() = Json.encodeToString(this)
}

object MemoryCookieJarSerializer: KSerializer<MemoryCookieJar> {
    override val descriptor =
        PrimitiveSerialDescriptor("hamusutax.okhttp.PersistentCookieJar", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) =
        MemoryCookieJar(
            decoder.decodeSerializableValue<Map<String, List<String>>>()
                .mapValues { (host, cookies) ->
                    cookies.map { it.toCookie(host) }
                }
        )

    override fun serialize(encoder: Encoder, value: MemoryCookieJar) {
        encoder.encodeSerializableValue(value.cookieStore.mapValues { it.value.map { it.toString() } })
    }
}
