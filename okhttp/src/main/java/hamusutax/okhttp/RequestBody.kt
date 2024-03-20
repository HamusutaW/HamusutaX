@file:Suppress("unused")
package hamusutax.okhttp

import okhttp3.RequestBody
import okio.Buffer
import java.nio.charset.Charset

fun RequestBody.payload(charset: Charset = Charsets.UTF_8): String {
    val buffer = Buffer()
    writeTo(buffer)
    return buffer.readString(charset)
}
