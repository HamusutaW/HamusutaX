@file:Suppress("unused")
package hamusutax.android

import android.content.SharedPreferences
import hamusutax.number.bitsToDouble
import hamusutax.number.toLongBits
import kotlinx.io.IOException

fun SharedPreferences.getString(key: String) =
    (if (contains(key)) getString(key, "") else null)
        ?: throw IOException("No value with key \"$key\" in SharedPreferences!")

fun SharedPreferences.getStringOrNull(key: String) =
    if (contains(key)) getString(key, "") else null

fun SharedPreferences.getStringSet(key: String): Set<String> =
    (if (contains(key)) getStringSet(key, emptySet()) else null)
        ?: throw IOException("No value with key \"$key\" in SharedPreferences!")

fun SharedPreferences.getStringSetOrNull(key: String): Set<String>? =
    if (contains(key)) getStringSet(key, emptySet()) else null

fun SharedPreferences.getInt(key: String) =
    if (contains(key)) getInt(key, 0) else null
        ?: throw IOException("No value with key \"$key\" in SharedPreferences!")

fun SharedPreferences.getIntOrNull(key: String) =
    if (contains(key)) getInt(key, 0) else null

fun SharedPreferences.getLong(key: String) =
    if (contains(key)) getLong(key, 0) else null
        ?: throw IOException("No value with key \"$key\" in SharedPreferences!")

fun SharedPreferences.getLongOrNull(key: String) =
    if (contains(key)) getLong(key, 0) else null

fun SharedPreferences.getFloat(key: String) =
    if (contains(key)) getFloat(key, 0F) else null
        ?: throw IOException("No value with key \"$key\" in SharedPreferences!")

fun SharedPreferences.getFloatOrNull(key: String) =
    if (contains(key)) getFloat(key, 0F) else null

fun SharedPreferences.getDouble(key: String) =
    if (contains(key)) getLong(key, 0).bitsToDouble() else null
        ?: throw IOException("No value with key \"$key\" in SharedPreferences!")

fun SharedPreferences.getDoubleOrNull(key: String) =
    if (contains(key)) getLong(key, 0).bitsToDouble() else null

fun SharedPreferences.getBoolean(key: String) =
    if (contains(key)) getBoolean(key, false) else null
        ?: throw IOException("No value with key \"$key\" in SharedPreferences!")

fun SharedPreferences.getBooleanOrNull(key: String) =
    if (contains(key)) getBoolean(key, false) else null

inline fun <reified T: Enum<T>> SharedPreferences.getEnum(key: String): T =
    if (contains(key)) enumValueOf(getString(key = key)) else null
        ?: throw IOException("No value with key \"$key\" in SharedPreferences!")

inline fun <reified T: Enum<T>> SharedPreferences.getEnumOrNull(key: String): T? =
    if (contains(key)) enumValueOf<T>(getString(key = key)) else null

fun SharedPreferences.putString(key: String, value: String) =
    edit().putString(key, value).apply()

fun SharedPreferences.putStringSet(key: String, value: Set<String>) =
    edit().putStringSet(key, value).apply()

fun SharedPreferences.putBoolean(key: String, value: Boolean) =
    edit().putBoolean(key, value).apply()

fun SharedPreferences.putInt(key: String, value: Int) =
    edit().putInt(key, value).apply()

fun SharedPreferences.putLong(key: String, value: Long) =
    edit().putLong(key, value).apply()

fun SharedPreferences.putFloat(key: String, value: Float) =
    edit().putFloat(key, value).apply()

fun SharedPreferences.putDouble(key: String, value: Double) =
    edit().putLong(key, value.toLongBits()).apply()

fun SharedPreferences.clear() =
    edit().clear().apply()
