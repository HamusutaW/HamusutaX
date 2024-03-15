@file:Suppress("unused")
package hamusutax.okhttp

import okhttp3.Request
import okhttp3.RequestBody
import okio.Buffer
import java.nio.charset.Charset
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun buildRequest(builderAction: Request.Builder.() -> Unit): Request {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return Request.Builder().apply(builderAction).build()
}

fun RequestBody.payload(charset: Charset = Charsets.UTF_8): String {
    val buffer = Buffer()
    writeTo(buffer)
    return buffer.readString(charset)
}
