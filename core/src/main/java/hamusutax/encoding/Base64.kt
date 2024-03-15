@file:Suppress("unused")
package hamusutax.encoding

import kotlin.io.encoding.Base64

fun ByteArray.encodeToBase64(startIndex: Int = 0, endIndex: Int = size) =
    Base64.encode(this, startIndex = startIndex, endIndex = endIndex)

fun String.decodeFromBase64(startIndex: Int = 0, endIndex: Int = length) =
    Base64.decode(this, startIndex = startIndex, endIndex = endIndex)
