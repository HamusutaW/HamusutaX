@file:Suppress("unused")
package hamusutax.android.intent

import android.content.Intent
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> Intent.putJsonExtra(name: String, value: T): Intent {
    val serialized = Json.encodeToString(value)
    return putExtra(name, serialized)
}

inline fun <reified T> Intent.getJsonExtra(name: String): T? {
    val value = getStringExtra(name) ?: return null
    return Json.decodeFromString<T>(value)
}
