package com.ohadsa.afeka

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ohadsa.afeka.Composable.ContentWrapper

@Composable
fun RowScope.Icon(
    imageVector: ImageVector,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    background: Color = Color.White,
    iconSize: Dp = 16.dp,
    weight: Float = 1.0f,
    onClick: () -> Unit,
) {
    ContentWrapper(
        background = background,
        weight = weight,
        onClick = onClick
    ) {
        Icon(
            imageVector,
            contentDescription,
            modifier = Modifier.requiredSize(iconSize),
            tint = tint
        )
    }
}

@Composable
fun RowScope.Icon(
    background: Color = Color.White,
    painter: Painter,
    contentDescription: String?,
    iconSize: Dp = 16.dp,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    weight: Float = 1.0f,
    onClick: () -> Unit,
) {
    ContentWrapper(
        background = background,
        weight = weight,
        onClick = onClick
    ) {
        Icon(
            painter,
            contentDescription,
            modifier = Modifier.requiredSize(iconSize),
            tint = tint
        )
    }
}

@Composable
fun RowScope.Icon(
    background: Color = Color.White,
    bitmap: ImageBitmap,
    contentDescription: String?,
    iconSize: Dp = 16.dp,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    weight: Float = 1.0f,
    onClick: () -> Unit,
) {
    ContentWrapper(
        background = background,
        weight = weight,
        onClick = onClick
    ) {
        Icon(
            bitmap,
            contentDescription,
            modifier = Modifier.requiredSize(iconSize),
            tint = tint
        )
    }
}
