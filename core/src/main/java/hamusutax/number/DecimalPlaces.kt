@file:Suppress("unused")
package hamusutax.number

import java.text.DecimalFormat

fun Float.toDecimalPlaces(places: Int, trimEnd: Boolean = false): String =
    if (trimEnd) {
        val decimalFormat = DecimalFormat("0." + "#".repeat(places))
        decimalFormat.format(this)
    } else "%.${places}f".format(this)

fun Double.toDecimalPlaces(places: Int, trimEnd: Boolean = false): String =
    if (trimEnd) {
        val decimalFormat = DecimalFormat("0." + "#".repeat(places))
        decimalFormat.format(this)
    } else "%.${places}f".format(this)
