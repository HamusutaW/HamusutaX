@file:Suppress("unused")
package hamusutax.number

fun Int.alignTo(base: Int) =
    ((this + base - 1) / base) * base

fun Long.alignTo(base: Int) =
    ((this + base - 1) / base) * base
