@file:Suppress("unused")
package hamusutax.number

import java.nio.ByteBuffer

fun Short.toByteArray(): ByteArray {
    val buffer = ByteBuffer.allocate(Short.SIZE_BYTES)
    buffer.putShort(this)
    return buffer.array()
}

fun Int.toByteArray(): ByteArray {
    val buffer = ByteBuffer.allocate(Int.SIZE_BYTES)
    buffer.putInt(this)
    return buffer.array()
}

fun Long.toByteArray(): ByteArray {
    val buffer = ByteBuffer.allocate(Long.SIZE_BYTES)
    buffer.putLong(this)
    return buffer.array()
}

@JvmName("shortArrayToByteArray")
fun List<Short>.toByteArray(): ByteArray {
    val buffer = ByteBuffer.allocate(size * Short.SIZE_BYTES)
    forEach { buffer.putShort(it) }
    return buffer.array()
}

@JvmName("intArrayToByteArray")
fun List<Int>.toByteArray(): ByteArray {
    val buffer = ByteBuffer.allocate(size * Int.SIZE_BYTES)
    forEach { buffer.putInt(it) }
    return buffer.array()
}

@JvmName("longArrayToByteArray")
fun List<Long>.toByteArray(): ByteArray {
    val buffer = ByteBuffer.allocate(size * Long.SIZE_BYTES)
    forEach { buffer.putLong(it) }
    return buffer.array()
}
