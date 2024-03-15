@file:Suppress("unused")
package hamusutax.android

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri
import hamusutax.android.intent.buildIntent

fun Activity.startActivity(uri: Uri) =
    startActivity(Intent(Intent.ACTION_VIEW, uri))

fun Activity.startActivity(uri: String) =
    startActivity(uri.toUri())

fun Application.startActivity(uri: Uri, flags: Int = Intent.FLAG_ACTIVITY_NEW_TASK) =
    startActivity(
        buildIntent(Intent.ACTION_VIEW, uri) {
            this@buildIntent.flags = flags
        }
    )

fun Application.startActivity(uri: String) =
    startActivity(uri.toUri())

fun Application.startActivity(clazz: Class<*>, uri: Uri? = null, flags: Int = Intent.FLAG_ACTIVITY_NEW_TASK) =
    startActivity(
        buildIntent(clazz) {
            data = uri
            this@buildIntent.flags = flags
        }
    )
