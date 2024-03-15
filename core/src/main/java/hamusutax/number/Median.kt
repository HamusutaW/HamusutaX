@file:Suppress("unused")
package hamusutax.number

@JvmName("medianOfInt")
fun List<Int>.median() =
    map { it.toLong() }.median()

@JvmName("medianOfLong")
fun List<Long>.median(): Double {
    if (isEmpty())
        return 0.0
    val sortedMap = sorted()
    return if (size % 2 == 0) (sortedMap[size / 2 - 1] + sortedMap[size / 2]) / 2.0
    else sortedMap[(size - 1) / 2].toDouble()
}
