@file:Suppress("unused")
package hamusutax.bytearray

fun ByteArray.padStart(newSize: Int, padByte: Byte = 0): ByteArray {
    require(newSize >= size) { "Target size is smaller than current size!" }
    return ByteArray(newSize - size).also {
        if (padByte != 0.toByte()) it.fill(padByte)
    } + this
}

fun ByteArray.padEnd(newSize: Int, padByte: Byte = 0): ByteArray {
    require(newSize >= size) { "Target size is smaller than current size!" }
    return this + ByteArray(newSize - size).also {
        if (padByte != 0.toByte()) it.fill(padByte)
    }
}

fun ByteArray.toBinaryString(): String =
    buildString {
        this@toBinaryString.forEach {
            append(it.toString(2))
        }
    }

/**
 * 将字节序列调整至指定大小，若小于当前大小则从尾部截断，若大于当前大小则使用特定字节填充（默认为零）
 */
fun ByteArray.sliceArrayOrPadEnd(newSize: Int, padByte: Byte = 0) =
    if (newSize == size) this
    else if (newSize < size) sliceArray(0..<newSize)
    else padEnd(newSize, padByte)
