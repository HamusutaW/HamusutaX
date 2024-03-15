@file:Suppress("unused")
package hamusutax.tuple

import java.io.Serializable

data class Quadruple<out A, out B, out C, out D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
) : Serializable {
    override fun toString() = "($first, $second, $third, $fourth)"
}

fun <T> Quadruple<T, T, T, T>.toList() =
    listOf(first, second, third, fourth)

inline fun <reified T> Quadruple<T, T, T, T>.toArray() =
    arrayOf(first, second, third, fourth)
