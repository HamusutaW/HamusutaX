@file:Suppress("unused")
package hamusutax.android

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import java.util.UUID
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun Context.buildNotification(channelId: String, builderAction: NotificationCompat.Builder.() -> Unit): Notification {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return NotificationCompat.Builder(this, channelId).apply(builderAction).build()
}

@RequiresApi(Build.VERSION_CODES.O)
fun Context.createNotificationChannel(channel: NotificationChannel) {
    with(notificationManager) {
        getNotificationChannel(channel.id)?.let {
            createNotificationChannel(channel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun Context.deleteNotificationChannel(channel: NotificationChannel) =
    deleteNotificationChannel(channel.id)

@RequiresApi(Build.VERSION_CODES.O)
fun Context.deleteNotificationChannel(channelId: String) {
    with(notificationManager) {
        getNotificationChannel(channelId)?.let {
            deleteNotificationChannel(channelId)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun Context.deleteAllNotificationChannel() {
    with(notificationManager) {
        notificationChannels.forEach {
            deleteNotificationChannel(it)
        }
    }
}

fun Context.notify(id: Int = UUID.randomUUID().hashCode(), notification: Notification) {
    with(notificationManagerCompat) {
        notify(id, notification)
    }
}
