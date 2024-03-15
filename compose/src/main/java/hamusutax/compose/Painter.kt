@file:Suppress("unused")
package hamusutax.compose

import android.content.res.Resources
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap

/**
 * 将图片资源转为 Painter
 *
 * 等效于 painterResource(id)，但是 painterResource 无法处理 XML 图片
 */
@Composable
fun rememberResourceBitmapPainter(@DrawableRes id: Int): BitmapPainter {
    val context = LocalContext.current
    return remember(id) {
        val drawable = ContextCompat.getDrawable(context, id)
            ?: throw Resources.NotFoundException()
        BitmapPainter(drawable.toBitmap().asImageBitmap())
    }
}
