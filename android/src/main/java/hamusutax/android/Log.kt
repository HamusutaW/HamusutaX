@file:Suppress("unused")
package hamusutax.android

import android.util.Log

fun Throwable.reportLog(message: String? = this.message, tag: String = this::class.simpleName ?: "<UnknownException>") =
    Log.e(tag, buildString {
        append("${this@reportLog::class.qualifiedName}: $localizedMessage")
        message?.let { append(" | $it") }
    })
