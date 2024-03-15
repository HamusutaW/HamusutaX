@file:Suppress("unused")
package hamusutax.datetime

import java.text.SimpleDateFormat
import java.util.Locale

fun Long.secondsToDateSting(
    pattern: String = "yyyy-MM-dd HH:mm:ss",
    locale: Locale = Locale.getDefault()
): String =
    SimpleDateFormat(pattern, locale).format(this * 1000)

fun Long.millisToDateString(
    pattern: String = "yyyy-MM-dd HH:mm:ss.SSS",
    locale: Locale = Locale.getDefault()
): String =
    SimpleDateFormat(pattern, locale).format(this)
