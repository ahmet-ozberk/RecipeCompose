package com.ao.recipeapp.ui.component

import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density

class SmoothCornerShape(private val cornerRadius: Float) : Shape {
    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: androidx.compose.ui.unit.LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = Path().apply {
                val radius = cornerRadius
                moveTo(radius, 0f)
                lineTo(size.width - radius, 0f)
                quadraticBezierTo(size.width, 0f, size.width, radius)
                lineTo(size.width, size.height - radius)
                quadraticBezierTo(size.width, size.height, size.width - radius, size.height)
                lineTo(radius, size.height)
                quadraticBezierTo(0f, size.height, 0f, size.height - radius)
                lineTo(0f, radius)
                quadraticBezierTo(0f, 0f, radius, 0f)
                close()
            }
        )
    }
}