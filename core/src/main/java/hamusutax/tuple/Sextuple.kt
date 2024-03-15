@file:Suppress("unused")
package hamusutax.tuple

import java.io.Serializable

data class Sextuple<out A, out B, out C, out D, out E, out F>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E,
    val sixth: F
) : Serializable {
    override fun toString() = "($first, $second, $third, $fourth, $fifth, $sixth)"
}

fun <T> Sextuple<T, T, T, T, T, T>.toList() =
    listOf(first, second, third, fourth, fifth, sixth)

inline fun <reified T> Sextuple<T, T, T, T, T, T>.toArray() =
    arrayOf(first, second, third, fourth, fifth, sixth)
