@file:Suppress("unused")
package hamusutax.tuple

import java.io.Serializable

data class Octuple<out A, out B, out C, out D, out E, out F, out G, out H>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E,
    val sixth: F,
    val seventh: G,
    val eighth: H
) : Serializable {
    override fun toString() =
        "($first, $second, $third, $fourth, $fifth, $sixth, $seventh, $eighth)"
}

fun <T> Octuple<T, T, T, T, T, T, T, T>.toList() =
    listOf(first, second, third, fourth, fifth, sixth, seventh, eighth)

inline fun <reified T> Octuple<T, T, T, T, T, T, T, T>.toArray() =
    arrayOf(first, second, third, fourth, fifth, sixth, seventh, eighth)
