@file:Suppress("UNUSED")
package hamusutax.core.compose.modifier

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

/**
 * 不显示点击效果的点击
 */
@OptIn(ExperimentalFoundationApi::class)
fun Modifier.clickableNoIndication(
    onLongClick: (() -> Unit)? = null,
    onClick: () -> Unit
): Modifier =
    this.composed {
        Modifier.combinedClickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onLongClick = onLongClick,
            onClick = onClick,
        )
    }