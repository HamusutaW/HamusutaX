@file:Suppress("unused")
package hamusutax.tuple

import java.io.Serializable

data class Tredecuple<out A, out B, out C, out D, out E, out F, out G, out H, out I, out J, out K, out L, out M>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E,
    val sixth: F,
    val seventh: G,
    val eighth: H,
    val ninth: I,
    val tenth: J,
    val eleventh: K,
    val twelfth: L,
    val thirteenth: M
) : Serializable {
    override fun toString() =
        "($first, $second, $third, $fourth, $fifth, $sixth, $seventh, $eighth, $ninth, $tenth, $eleventh, $twelfth, $thirteenth)"
}

fun <T> Tredecuple<T, T, T, T, T, T, T, T, T, T, T, T, T>.toList() =
    listOf(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth, thirteenth)

inline fun <reified T> Tredecuple<T, T, T, T, T, T, T, T, T, T, T, T, T>.toArray() =
    arrayOf(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth, thirteenth)
