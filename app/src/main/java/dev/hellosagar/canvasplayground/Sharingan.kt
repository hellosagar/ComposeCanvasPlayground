package dev.hellosagar.canvasplayground

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun Sharingan() {

    val millisDuration = 1500
    val orangeColor = Color(0xFFD55654)
    val blackOrangishBgColor = Color(0xFF160909)

    var alphaValue by remember { mutableStateOf(0f) }
    val animatedAlpha by animateFloatAsState(
        targetValue = alphaValue,
        animationSpec = tween(
            durationMillis = millisDuration * 2,
            easing = FastOutSlowInEasing
        )
    )

    var scaleValue by remember { mutableStateOf(1.3f) }
    val animatedScale by animateFloatAsState(
        targetValue = scaleValue,
        animationSpec = tween(
            durationMillis = millisDuration,
            easing = LinearEasing
        )
    )

    var angleValue by remember { mutableStateOf(0f) }
    val animatedAngle by animateFloatAsState(
        targetValue = angleValue * 1.5f,
        animationSpec = tween(
            durationMillis = millisDuration + 2000,
            easing = FastOutSlowInEasing
        )
    )

    var bgScaleValue by remember { mutableStateOf(1f) }
    val animatedBgScale by animateFloatAsState(
        targetValue = alphaValue,
    )

    LaunchedEffect(Unit) {
        scaleValue = 1f
        angleValue = -720f
        delay(millisDuration.toLong() + 1000)
        angleValue = 720f
        delay(millisDuration.toLong())
        scaleValue = 1.4f
        alphaValue = 1f
        delay(millisDuration.toLong() - 1000)
        bgScaleValue = 10f
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {

        val w = this.size.width
        val h = this.size.height
        val gradientCircleRadius = w.times(.31f)
        val outerRadius = w.times(.28f)
        val innerOrangeRadius = w.times(.25f)
        val coreBlackRadius = w.times(.05f)
        val blackStrokeRadius = w.times(.16f)
        val beadRadius = w.times(.030f)

        withTransform({
            rotate(degrees = animatedAngle)
            scale(scale = animatedScale)
        }) {

            // Background spread color circle
            scale(
                scale = animatedBgScale
            ) {
                drawCircle(
                    color = blackOrangishBgColor,
                    radius = w.times(.10f)
                )
            }

            // Gradient circle
            drawCircle(
                radius = gradientCircleRadius,
                brush = Brush.radialGradient(
                    listOf(Color.White, Color.Transparent),
                    center = center,
                    radius = gradientCircleRadius
                )
            )

            // Black Outer circle
            drawCircle(
                radius = outerRadius,
                color = Color.Black
            )

            // Inner Orange circle
            drawCircle(
                radius = innerOrangeRadius,
                color = orangeColor
            )

            // Black stroked circle
            drawCircle(
                radius = blackStrokeRadius,
                color = Color.Black,
                style = Stroke(width = 6f)
            )

            // Core Black circle
            drawCircle(
                radius = coreBlackRadius,
                color = Color.Black
            )

            // 3 Beads
            val arc = Path().apply {
                val offset = blackStrokeRadius + 2
                moveTo(-offset, 23f)
                relativeQuadraticBezierTo(
                    dx1 = -50f,
                    dy1 = 10f,
                    dx2 = -66f,
                    dy2 = -70f
                )
                relativeQuadraticBezierTo(
                    dx1 = 26f,
                    dy1 = 48f,
                    dx2 = 60f,
                    dy2 = 40f
                )
                close()
            }

            translate(
                left = center.x,
                top = center.y
            ) {
                repeat(3) {
                    rotate(
                        degrees = (120 * it).toFloat(),
                        pivot = Offset.Zero
                    ) {

                        drawCircle(
                            radius = beadRadius,
                            color = Color.Black.copy(1 - animatedAlpha),
                            center = Offset(-blackStrokeRadius, 0f)
                        )

                        drawPath(
                            path = arc,
                            color = Color.Black.copy(1 - animatedAlpha)
                        )

                    }
                }
            }

            // Ninja Arc
            val innerRadiusStroke = -w.times(.24f)
            val halfInnerRadiusStroke = -w.times(.24f) / 2

            val ninjaStarPath = Path().apply {
                val xInitial = -40f
                val yInitial = -60f

                moveTo(xInitial, yInitial)

                lineTo(
                    20f,
                    14f
                )

                cubicTo(
                    x1 = -50f, y1 = 120f,
                    x2 = halfInnerRadiusStroke - 50, y2 = 110f,
                    x3 = innerRadiusStroke, y3 = 53f,
                )

                cubicTo(
                    x1 = halfInnerRadiusStroke - 90f, y1 = 75f,
                    x2 = halfInnerRadiusStroke + 76, y2 = 70f,
                    x3 = xInitial, y3 = yInitial,
                )

                close()
            }

            translate(
                left = center.x,
                top = center.y
            ) {

                rotate(
                    degrees = 30f,
                    pivot = Offset.Zero
                ) {
                    scale(
                        1f,
                        pivot = Offset.Zero
                    ) {
                        repeat(3) {
                            rotate(
                                (120 * it).toFloat(),
                                pivot = Offset.Zero
                            ) {
                                drawPath(
                                    path = ninjaStarPath,
                                    color = Color.Black.copy(animatedAlpha)
                                )
                            }
                        }
                        drawCircle(
                            radius = 30f,
                            color = orangeColor.copy(animatedAlpha),
                            center = Offset.Zero
                        )
                    }
                }
            }

        }

    }

}

@Preview
@Composable
fun Preview() {
    Sharingan()
}