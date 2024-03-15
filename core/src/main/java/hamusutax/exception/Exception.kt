@file:Suppress("unused")
package hamusutax.exception

fun unsupported(reason: String = "Not used."): Nothing =
    throw UnsupportedOperationException(reason)
