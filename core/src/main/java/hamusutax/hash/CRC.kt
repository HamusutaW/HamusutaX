@file:Suppress("unused")
package hamusutax.hash

import androidx.annotation.RequiresApi
import java.util.zip.CRC32
import java.util.zip.CRC32C

fun ByteArray.crc32(): Long {
    val digest = CRC32()
    digest.update(this)
    return digest.value
}

@RequiresApi(34)
fun ByteArray.crc32c(): Long {
    val digest = CRC32C()
    digest.update(this)
    return digest.value
}
