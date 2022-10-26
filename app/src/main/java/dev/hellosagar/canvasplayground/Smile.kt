package dev.hellosagar.canvasplayground

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun Smile(modifier: Modifier = Modifier){
    Canvas(modifier = modifier.size(200.dp).background(color = Color.Gray)) {

        // Circle outline
        drawCircle(
            brush = Brush.linearGradient(
                colors = listOf(Color.Blue, Color.Cyan)
            ),
            radius = size.width / 2,
            center = center,
            style = Stroke(width = size.width * 0.055f)
        )

        // Smile/Mouth
        val smilePadding = size.width * 0.15f
        drawArc(
            color = Color.Red,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(smilePadding, smilePadding),
            size = Size(size.width - (smilePadding * 2f), size.height - (smilePadding * 2f))
        )

        // Left eye
        drawRect(
            color = Color.Green,
            topLeft = Offset(size.width * 0.25f, size.height * 4f),
            size = Size(smilePadding, smilePadding)
        )

        // Right eye
        drawRect(
            color = Color.Green,
            topLeft = Offset(size.width * 0.75f, size.height * 4f),
            size = Size(smilePadding, smilePadding)
        )

    }
}