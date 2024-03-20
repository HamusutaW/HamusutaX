@file:Suppress("unused")
package hamusutax.okhttp

import okhttp3.Cookie
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun buildCookie(builderAction: Cookie.Builder.() -> Unit): Cookie {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return Cookie.Builder().apply(builderAction).build()
}

/**
 * 将 Fiddler 格式的 Cookie 字符串转为 Map 形式
 * @throws IllegalArgumentException 格式不正确时抛出异常
 */
fun String.toCookies() =
    if (isBlank()) emptyMap()
    else split(";").associate { cookie ->
        val kv = cookie.split("=").map { it.trim() }
        require(kv.size != 2 && kv[0] == "") { "If this is not a well-formed Fiddler Cookies." }
        kv[0] to kv[1]
    }
