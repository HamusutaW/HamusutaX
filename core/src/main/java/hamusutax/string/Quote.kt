@file:Suppress("unused")
package hamusutax.string

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.Charset

/**
 * 进行 `UrlEncode` 编码（不编码 `'+'` 字符）
 */
fun String.quote(charset: Charset = Charsets.UTF_8): String =
    URLEncoder.encode(this, charset.name())

/**
 * 进行 `UrlEncode` 编码（`'+'` 字符将编码为 `"%20"`）
 */
fun String.quotePlus(charset: Charset = Charsets.UTF_8): String =
    URLEncoder.encode(this, charset.name()).replace("+", "%20")

/**
 * 对 UrlDecode 解码
 */
fun String.unquote(charset: Charset = Charsets.UTF_8): String =
    URLDecoder.decode(this, charset.name())
