package dev.hellosagar.canvasplayground

import android.graphics.Paint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun InstagramIcon() {
    val instaColors = listOf(Color.Yellow, Color.Red, Color.Magenta)

    Canvas(
        modifier = Modifier
            .size(100.dp)
            .padding(16.dp)
    ) {

        drawRoundRect(
            brush = Brush.linearGradient(colors = instaColors),
            cornerRadius = CornerRadius(60f, 60f),
            style = Stroke(width = 15f, cap = StrokeCap.Round)
        )

        drawCircle(
            brush = Brush.linearGradient(instaColors),
            radius = 45f,
            style = Stroke(15f, cap = StrokeCap.Round)
        )

        drawCircle(
            brush = Brush.linearGradient(instaColors),
            radius = 8f,
            center = Offset(this.size.width * .80f, this.size.height * .20f),
        )

    }

}

@Composable
fun FacebookIcon() {
    val paint = Paint().apply {
        textAlign = Paint.Align.CENTER
        textSize = 200f
        color = Color.White.toArgb()
//        typeface = Typeface.createFromAsset(assetManager, "FACEBOLF.OTF")
    }
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .padding(16.dp)
    ) {
        drawRoundRect(
            color = Color(0xFF1776d1), cornerRadius = CornerRadius(20f, 20f)
        )
        drawContext.canvas.nativeCanvas
        drawContext.canvas.nativeCanvas.drawText("f", center.x + 25, center.y + 80, paint)
    }
}

@Composable
fun MessengerIcon() {
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .padding(16.dp)
    ) {

        val colors = listOf(Color(0xFF02b8f9), Color(0xFF0277fe))

        val w = this.size.width
        val h = this.size.height

        val trainglePath = Path().let {
            it.moveTo(this.size.width * .20f, this.size.width * .77f)
            it.lineTo(this.size.width * .20f, this.size.width * .95f)
            it.lineTo(this.size.width * .37f, this.size.width * .84f)
            it.close()
            it
        }

        val lightningPath = Path().let {
            it.moveTo(w * .22f, h * .60f)
            it.lineTo(w * .46f, h * .42f)
            it.lineTo(w * .58f, h * .50f)
            it.lineTo(w * .75f, h * .38f)

            it.moveTo(w * .22f, h * .60f)
            it.lineTo(w * .42f, h * .50f)
            it.lineTo(w * .54f, h * .58f)
            it.lineTo(w * .75f, h * .38f)
            it
        }

        drawOval(
            brush = Brush.verticalGradient(colors),
            size = Size(this.size.width, this.size.height * .95f)
        )

        drawPath(
            path = lightningPath,
            color = Color.White,
//            style = Stroke(width = 2f)
        )

        drawPath(
            path = trainglePath,
            brush = Brush.verticalGradient(colors),
            style = Stroke(width = 15f, cap = StrokeCap.Round)
        )

    }
}

@Composable
fun GooglePhotosIcon() {
    Canvas(
        modifier = Modifier
            .size(400.dp)
            .padding(16.dp)
            .background(color = Color.Cyan)
    ) {

        val w = this.size.width
        val h = this.size.height

        drawArc(
            color = Color.Green,
            startAngle = 0f,
            sweepAngle = -180f,
            useCenter = false,
            topLeft = Offset(w * 0f, h * 0.25f),
            size = Size(w * .5f, h * .5f)
        )

        drawArc(
            color = Color.Red,
            startAngle = -90f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(w * .25f, h * 0f),
            size = Size(w * .5f, h * .5f)
        )

        drawArc(
            color = Color.Blue,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(w * .5f, h * 0.25f),
            size = Size(w * .5f, h * .5f)
        )

        drawArc(
            color = Color.Yellow,
            startAngle = -90f,
            sweepAngle = -180f,
            useCenter = false,
            topLeft = Offset(w * .25f, h * 0.5f),
            size = Size(w * .5f, h * .5f)
        )

    }
}

@Composable
fun WeatherAppIcon() {
    Canvas(
        modifier = Modifier
            .size(200.dp)
            .padding(16.dp)
    ) {

        val backgroundColor = listOf(Color(0xFF2078EE), Color(0xFF74E6FE))
        val sunColor = listOf(Color(0xFFFFC200), Color(0xFFFFE100))

        val w = this.size.width
        val h = this.size.height

        val path = Path().apply {
            moveTo(w.times(.76f), h.times(.72f))
//            lineTo(w.times(.93f),  h.times(.72f))
            cubicTo(
                x1 = w.times(.93f),
                y1 = h.times(.72f),
                x2 = w.times(.98f),
                y2 = h.times(.41f),
                x3 = w.times(.76f),
                y3 = h.times(.40f)
            )
            cubicTo(
                x1 = w.times(.72f),
                y1 = h.times(.20f),
                x2 = w.times(.40f),
                y2 = h.times(.24f),
                x3 = w.times(.42f),
                y3 = h.times(.40f)
            )

            cubicTo(
                x1 = w.times(.22f),
                y1 = h.times(.42f),
                x2 = w.times(.20f),
                y2 = h.times(.70f),
                x3 = w.times(.42f),
                y3 = h.times(.72f)
            )
            close()
        }

        drawRoundRect(
            brush = Brush.verticalGradient(backgroundColor), cornerRadius = CornerRadius(80f, 80f)
        )

        drawCircle(
            color = Color.Yellow,
            radius = w.times(.18f),
            center = Offset(w.times(.30f), h.times(.35f))
        )

        drawPath(
            path = path,
            color = Color.White.copy(alpha = .55f),
        )
    }
}