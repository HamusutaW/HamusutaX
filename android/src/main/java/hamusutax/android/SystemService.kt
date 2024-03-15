@file:Suppress("unused")
package hamusutax.android

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

val Context.notificationManager
    get() = ContextCompat.getSystemService(this, NotificationManager::class.java)!!

val Context.notificationManagerCompat
    get() = NotificationManagerCompat.from(this)
