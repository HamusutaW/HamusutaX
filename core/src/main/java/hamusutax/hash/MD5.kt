@file:Suppress("unused")
package hamusutax.hash

import java.nio.charset.Charset
import java.security.MessageDigest

fun ByteArray.md5(length: Int = 32): ByteArray {
    require(length in 0..32 && length % 2 == 0)
    return MessageDigest.getInstance("MD5").digest(this)
        .sliceArray((16 - length / 2)..<(16 + length / 2))
}

fun String.md5(length: Int = 32, charset: Charset = Charsets.UTF_8) =
    toByteArray(charset).md5(length)
