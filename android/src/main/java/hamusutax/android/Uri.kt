@file:Suppress("unused")
package hamusutax.android

import android.content.ContentResolver
import android.net.Uri
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.charset.Charset

fun Uri.writeText(contentResolver: ContentResolver, text: String, charset: Charset = Charsets.UTF_8) {
    contentResolver.openFileDescriptor(this, "w")
        ?.use { parcelFileDescriptor ->
            FileOutputStream(parcelFileDescriptor.fileDescriptor).write(text.toByteArray(charset = charset))
        }
}

fun Uri.readText(contentResolver: ContentResolver, charset: Charset = Charsets.UTF_8): String =
    buildString {
        contentResolver.openFileDescriptor(this@readText, "r")
            ?.use { parcelFileDescriptor ->
                append(FileInputStream(parcelFileDescriptor.fileDescriptor).readBytes().toString(charset = charset))
            }
    }
