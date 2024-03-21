@file:Suppress("unused")
package hamusutax.bytearray

/**
 * 在字节序列前部填充
 * @throws IllegalArgumentException 目标大小小于原大小
 */
fun ByteArray.padStart(newSize: Int, padByte: Byte = 0): ByteArray {
    require(newSize >= size) { "Target size is smaller than current size!" }
    return ByteArray(newSize - size).also {
        if (padByte != 0.toByte()) it.fill(padByte)
    } + this
}

/**
 * 在字节序列尾部填充
 * @throws IllegalArgumentException 目标大小小于原大小
 */
fun ByteArray.padEnd(newSize: Int, padByte: Byte = 0): ByteArray {
    require(newSize >= size) { "Target size is smaller than current size!" }
    return this + ByteArray(newSize - size).also {
        if (padByte != 0.toByte()) it.fill(padByte)
    }
}

/**
 * 将字节序列转为二进制字符串
 */
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

/**
 * 展开字节序列
 */
fun List<ByteArray>.toByteArray() =
    flatMap { it.asList() }.toByteArray()

fun emptyByteArray() = ByteArray(0)
