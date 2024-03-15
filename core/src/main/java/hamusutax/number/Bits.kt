@file:Suppress("unused")
package hamusutax.number

fun Long.bitsToDouble() =
    java.lang.Double.longBitsToDouble(this)

fun Double.toLongBits() =
    java.lang.Double.doubleToLongBits(this)

/**
 * 返回整数的二进制补码表示，长度为 32 个字符
 *
 * 当整数为正数时，等效于 Int.toString(2)
 */
fun Int.toBinaryString(trimStart: Boolean = false): String =
    Integer.toBinaryString(this).let {
        if (trimStart) it
        else it.padStart(32, '0')
    }
