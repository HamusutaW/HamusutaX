@file:Suppress("unused")
package hamusutax.android.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun buildIntent(action: String, builderAction: Intent.() -> Unit): Intent {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return Intent(action).apply(builderAction)
}

inline fun buildIntent(action: String, uri: Uri, builderAction: Intent.() -> Unit): Intent {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return Intent(action, uri).apply(builderAction)
}

inline fun buildIntent(packageContext: Context, clazz: Class<*>, builderAction: Intent.() -> Unit): Intent {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return Intent(packageContext, clazz).apply(builderAction)
}

@JvmName("buildIntentInContext")
inline fun Context.buildIntent(clazz: Class<*>, builderAction: Intent.() -> Unit) =
    buildIntent(this, clazz, builderAction)

inline fun buildIntent(action: String, uri: Uri, packageContext: Context, clazz: Class<*>, builderAction: Intent.() -> Unit): Intent {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return Intent(action, uri, packageContext, clazz).apply(builderAction)
}

inline fun Context.buildIntent(action: String, uri: Uri, clazz: Class<*>, builderAction: Intent.() -> Unit) =
    buildIntent(action, uri, this, clazz, builderAction)
