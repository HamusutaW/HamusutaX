@file:Suppress("unused")
package hamusutax.okhttp

import okhttp3.Cookie
import okhttp3.HttpUrl
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun buildCookie(builderAction: Cookie.Builder.() -> Unit): Cookie {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return Cookie.Builder().apply(builderAction).build()
}

fun String.toCookie(host: String) =
    toCookieOrNull(host)!!

fun String.toCookieOrNull(host: String) =
    toCookieOrNull(
        buildHttpUrl {
            scheme("https")
            host(host)
        }
    )

fun String.toCookie(httpUrl: HttpUrl) =
    toCookieOrNull(httpUrl)!!

fun String.toCookieOrNull(httpUrl: HttpUrl) =
    Cookie.parse(httpUrl, this)

/**
 * 输入格式：key1=value1; key2=value2; key3=value3
 * @throws IllegalArgumentException 格式不正确时抛出异常
 */
fun String.toCookieStringMap() =
    split(";").associate { cookie ->
        with(cookie.trim().split("=")) {
            require(size == 2 && get(0) != "") { "If this is not a well-formed Fiddler Cookies." }
            get(0) to get(1)
        }
    }

val Cookie.NOT_PERSISTENT: Long
    get() = Long.MIN_VALUE
