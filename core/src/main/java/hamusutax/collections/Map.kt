@file:Suppress("unused")
package hamusutax.collections

import java.util.SortedMap

inline fun <T, R> Iterable<T>.mapCached(key: (T) -> Any? = { it }, transform: (T) -> R): List<R> {
    val caches = mutableMapOf<Int, R>()
    return map {
        caches.getOrPut(key = key(it).hashCode()) {
            transform(it)
        }
    }
}

fun <T> Map<ByteArray, T>.toSortedMap(): SortedMap<ByteArray, T> {
    val comparator = Comparator<ByteArray> { a, b ->
        repeat(minOf(a.size, b.size)) { index ->
            val compare = a[index].compareTo(b[index])
            if (compare != 0) return@Comparator compare
        }
        return@Comparator a.size.compareTo(b.size)
    }
    return toSortedMap(comparator)
}
