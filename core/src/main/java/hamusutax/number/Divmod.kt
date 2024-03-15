@file:Suppress("unused")
package hamusutax.number

fun Int.divmod(divisor: Int) =
    this / divisor to this % divisor

fun Long.divmod(divisor: Long) =
    this / divisor to this % divisor
