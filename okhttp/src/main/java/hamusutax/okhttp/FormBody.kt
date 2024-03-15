@file:Suppress("unused")
package hamusutax.okhttp

import okhttp3.FormBody
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

private val EmptyFormBody = FormBody.Builder().build()

fun emptyFormBody() = EmptyFormBody

inline fun buildFormBody(builderAction: FormBody.Builder.() -> Unit): FormBody {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return FormBody.Builder().apply(builderAction).build()
}
