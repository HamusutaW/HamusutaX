@file:Suppress("unused")
package hamusutax.android

import android.webkit.CookieManager
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

val cookieManager = CookieManager.getInstance()!!

class AndroidCookieJar : CookieJar {
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val urlString = url.toString()
        cookies.forEach { cookieManager.setCookie(urlString, it.toString()) }
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> = get(url)

    fun get(url: HttpUrl): List<Cookie> {
        val cookies = cookieManager.getCookie(url.toString())
        return if (cookies != null && cookies.isNotEmpty())
            cookies.split(";").mapNotNull { Cookie.parse(url, it) }
        else emptyList()
    }

    fun remove(url: HttpUrl, cookieNames: Set<String>? = null, maxAge: Int = -1): Int {
        val urlString = url.toString()
        val cookies = cookieManager.getCookie(urlString) ?: return 0

        fun List<String>.filterNames(): List<String> =
            if (cookieNames != null) this.filter { it in cookieNames }
            else this

        return cookies.split(";")
            .map { it.substringBefore("=") }
            .filterNames()
            .onEach { cookieManager.setCookie(urlString, "$it=;Max-Age=$maxAge") }
            .count()
    }

    fun removeAll() {
        cookieManager.removeAllCookies {}
    }
}
