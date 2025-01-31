package com.seiko.imageloader.util

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultFilterQuality
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import com.google.accompanist.drawablepainter.DrawablePainter
import com.seiko.imageloader.toPainter

fun Drawable.toPainter(filterQuality: FilterQuality = DefaultFilterQuality): Painter {
    return when (this) {
        is BitmapDrawable -> bitmap.toPainter(filterQuality)
        is ColorDrawable -> ColorPainter(Color(color))
        else -> DrawablePainter(mutate())
    }
}
