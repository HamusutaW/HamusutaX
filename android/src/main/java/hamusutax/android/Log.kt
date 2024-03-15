@file:Suppress("unused")
package hamusutax.android

import android.util.Log

fun Throwable.reportLog(tag: String = this::class.simpleName ?: "<UnknownException>", message: String? = null) =
    Log.e(tag, buildString {
        append("${this@reportLog::class.qualifiedName}: $localizedMessage")
        message?.let { append(" | $it") }
    })
