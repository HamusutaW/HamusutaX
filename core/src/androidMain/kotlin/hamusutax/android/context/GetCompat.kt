@file:Suppress("UNUSED")
package hamusutax.android.context

import android.content.Context
import androidx.core.app.LocaleManagerCompat
import androidx.core.app.NotificationManagerCompat

fun Context.getNotificationManagerCompat() =
    NotificationManagerCompat.from(this)

fun Context.getSystemLocales() =
    LocaleManagerCompat.getSystemLocales(this)

fun Context.getApplicationLocales() =
    LocaleManagerCompat.getApplicationLocales(this)
