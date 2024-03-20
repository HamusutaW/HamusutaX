@file:Suppress("unused")
package hamusutax.android

import android.webkit.CookieManager
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

val cookieManager by lazy { CookieManager.getInstance()!! }

/**
 * 来自 [Mihon](https://github.com/mihonapp/mihon/blob/main/core/common/src/main/kotlin/eu/kanade/tachiyomi/network/AndroidCookieJar.kt)
 */
class AndroidCookieJar : CookieJar {
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) =
        cookies.forEach { cookieManager.setCookie(url.toString(), it.toString()) }

    override fun loadForRequest(url: HttpUrl) = get(url)

    operator fun get(url: HttpUrl): List<Cookie> {
        val cookies = cookieManager.getCookie(url.toString())
        return if (cookies != null && cookies.isNotEmpty())
            cookies.split("; ").mapNotNull { Cookie.parse(url, it) }
        else emptyList()
    }

    operator fun set(url: HttpUrl, cookies: List<Cookie>) =
        saveFromResponse(url, cookies)

    fun removeAtEndOfSession(url: HttpUrl, cookieNames: List<String>? = null) =
        remove(url, cookieNames, -1)

    fun removeNow(url: HttpUrl, cookieNames: List<String>? = null) =
        remove(url, cookieNames, 0)

    fun clear() = cookieManager.removeAllCookies(null)

    private fun remove(url: HttpUrl, cookieNames: List<String>? = null, maxAge: Int): Int {
        val urlString = url.toString()
        // 格式：key1=value1; key2=value2; key3=value3
        val cookies = cookieManager.getCookie(urlString) ?: return 0
        return cookies.split("; ")
            .map { it.substringBefore("=") }
            .filter { if (cookieNames != null) it in cookieNames else true }
            .onEach { cookieManager.setCookie(urlString, "$it=; Max-Age=$maxAge") }
            .count()
    }
}
