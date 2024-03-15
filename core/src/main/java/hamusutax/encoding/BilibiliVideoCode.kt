@file:Suppress("unused")
package hamusutax.encoding

import kotlin.math.pow

/**
 * 将哔哩哔哩视频 av 号转为 BV 号
 *
 * 返回字符串以 `"BV"` 开头
 */
fun Long.bilibiliAvToBv(prefix: Boolean = true): String {
    val table = "fZodR9XQDSUm21yCkr6zBqiveYah8bt4xsWpHnJE7jL5VG3guMTKNPAwcF".toCharArray()
    val bv = "BV1  4 1 7  ".toCharArray()
    intArrayOf(11, 10, 3, 8, 4, 6).forEachIndexed { index, value ->
        bv[value] = table[
            (((this xor 177451812) + 8728348608) / 58.0.pow(index) % 58).toInt()
        ]
    }
    val result = bv.joinToString("")
    return if (prefix) result else result.substring(2)
}

/**
 * 将哔哩哔哩视频 BV 号转为 av 号
 *
 * 字符串需要以 `"BV"` 开头
 */
fun String.bilibiliBvToAv(): Long {
    require(length > 2 && slice(0..1) == "BV")
    val table = "fZodR9XQDSUm21yCkr6zBqiveYah8bt4xsWpHnJE7jL5VG3guMTKNPAwcF".toCharArray()
    val result = intArrayOf(11, 10, 3, 8, 4, 6)
        .mapIndexed { index, value ->
            table.indexOf(this[value]) * 58.0.pow(index).toLong()
        }.sum()
    return (result - 8728348608) xor 177451812
}
