@file:Suppress("unused")
package hamusutax.android

import android.util.Log

fun Throwable.reportLog(msg: String? = null, tag: String = this::class.simpleName ?: "<UnknownException>") =
    Log.e(tag, buildString {
        msg?.let { append("$it | ") }
        append(message)
    })
