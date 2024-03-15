@file:Suppress("unused")
package hamusutax.number

import kotlin.math.ceil
import kotlin.math.log
import kotlin.math.pow

/**
 * 是否为 n 的整数次幂（包含零次幂）
 */
fun Int.isPowerOf(n: Int) =
    toLong().isPowerOf(n)

/**
 * 是否为 n 的整数次幂（包含零次幂）
 */
fun Long.isPowerOf(n: Int): Boolean {
    require(this > 0 && n > 0) { "Number and base must be greater than zero." }
    return when (n) {
        2 -> this and (this - 1) == 0L
        else -> this == nextPowerOf(n)
    }
}

/**
 * 获取下一个 2 的整数次幂
 */
private fun Int.nextPowerOf2(): Int {
    var n = this - 1
    n = n or (n shr 1)
    n = n or (n shr 2)
    n = n or (n shr 4)
    n = n or (n shr 8)
    n = n or (n shr 16)
    return n + 1
}

/**
 * 获取下一个 2 的整数次幂
 */
private fun Long.nextPowerOf2(): Long {
    var n = this - 1
    n = n or (n shr 1)
    n = n or (n shr 2)
    n = n or (n shr 4)
    n = n or (n shr 8)
    n = n or (n shr 16)
    n = n or (n shr 24)
    n = n or (n shr 32)
    n = n or (n shr 40)
    n = n or (n shr 48)
    n = n or (n shr 56)
    return n + 1
}

/**
 * 获取下一个 n 的整数次幂
 */
fun Int.nextPowerOf(n: Int): Int {
    require(this > 0 && n > 0) { "Number and base must be greater than zero." }
    return when (n) {
        2 -> nextPowerOf2()
        else -> {
            val value = n.toDouble().pow(ceil(log(this.toDouble(), n.toDouble())))
            require(value <= Int.MAX_VALUE) { "Result is greater than Int.MAX_VALUE." }
            value.toInt()
        }
    }
}

/**
 * 获取下一个 n 的整数次幂
 */
fun Long.nextPowerOf(n: Int): Long {
    require(this > 0 && n > 0) { "Number and base must be greater than zero." }
    return when (n) {
        2 -> nextPowerOf2()
        else -> {
            val value = n.toDouble().pow(ceil(log(this.toDouble(), n.toDouble())))
            require(value <= Long.MAX_VALUE) { "Result is greater than Long.MAX_VALUE." }
            value.toLong()
        }
    }
}

/**
 * 快速计算 2 ^ [n]，[n] 的范围为 `0..31`
 */
fun pow2ToInt(n: Int): Int {
    require(n in 0..31)
    return if (n == 0) 1
    else 2 shl n - 1
}

/**
 * 快速计算 2 ^ [n]，[n] 的范围为 `0..63`
 */
fun pow2(n: Int): Long {
    require(n in 0..63)
    return if (n == 0) 1
    else 2L shl n - 1
}

/**
 * 快速计算 log_2 ([n])，此函数不会验证 [n] 是否为 2 的整数次幂
 */
fun log2ToInt(n: Int) =
    31 - n.countLeadingZeroBits()

/**
 * 快速计算 log_2 ([n])，此函数不会验证 [n] 是否为 2 的整数次幂
 */
fun log2ToInt(n: Long) =
    63 - n.countLeadingZeroBits()
