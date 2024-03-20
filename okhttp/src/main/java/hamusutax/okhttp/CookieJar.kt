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

@Serializable(PersistentCookieJarSerializer::class)
open class PersistentCookieJar internal constructor(
    cookies: Map<String, List<Cookie>> = emptyMap()
): CookieJar {
    val cookieStore = cookies.toMutableMap()
    override fun loadForRequest(url: HttpUrl) =
        (cookieStore[url.host] ?: emptyList()).filter { it.expiresAt > System.currentTimeMillis() && it.matches(url) }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieStore[url.host] =  ((cookieStore[url.host] ?: emptyList()) + cookies)
            .filter { it.expiresAt > System.currentTimeMillis() }
            .associateBy { it.name to it.path }
            .map { it.value }
    }

    override fun toString() = Json.encodeToString(this)
}

object PersistentCookieJarSerializer: KSerializer<PersistentCookieJar> {
    override val descriptor =
        PrimitiveSerialDescriptor("hamusutax.okhttp.PersistentCookieJar", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) =
        PersistentCookieJar(
            decoder.decodeSerializableValue<Map<String, List<String>>>()
                .mapValues { (host, cookies) ->
                    cookies.map { it.toCookie(host) }
                }
        )

    override fun serialize(encoder: Encoder, value: PersistentCookieJar) {
        encoder.encodeSerializableValue(value.cookieStore.mapValues { it.value.map { it.toString() } })
    }
}
