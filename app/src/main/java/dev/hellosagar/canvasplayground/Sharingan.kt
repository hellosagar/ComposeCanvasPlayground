package dev.hellosagar.canvasplayground

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    var aplhaValue by remember { mutableStateOf(0f) }
    val animatedApha by animateFloatAsState(
        targetValue = aplhaValue, animationSpec = tween(
            durationMillis = 3000,
            easing = LinearEasing
        )
    )

    val millisDuration = 2000
    var scaleValue by remember { mutableStateOf(1.3f) }
    val animatedSize by animateFloatAsState(
        targetValue = scaleValue, animationSpec = tween(
            durationMillis = millisDuration, easing = FastOutSlowInEasing
        )
    )

    var bgScaleValue by remember { mutableStateOf(1f) }
    val bgAnimatedScale by animateFloatAsState(
        targetValue = bgScaleValue,
    )

    var angleValue by remember { mutableStateOf(0f) }
    val animatedAngle by animateFloatAsState(
        targetValue = angleValue,
        animationSpec = tween(
            millisDuration + 2000,
            easing = FastOutSlowInEasing
        ),
    )

    val orange = Color(0xFFD55654)

    val orangishColor = Color(0xFF160909)

    LaunchedEffect(Unit) {
        scaleValue = .95f
        angleValue = -360f
        delay(millisDuration.toLong() + 800)
        angleValue = 720f
        aplhaValue = 1f
        scaleValue = 1.4f
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
        val gradientCircleRadium = w.times(.31f)

        withTransform({
            rotate(animatedAngle)
            scale(scale = animatedSize)
        }

        ) {

            // Background spread color circle
            scale(
                scale = bgAnimatedScale
            ) {
                drawCircle(
                    color = orangishColor, radius = w.times(.10f)
                )
            }

            // Gradient circle
            drawCircle(
                brush = Brush.radialGradient(
                    listOf(Color.White, Color.Transparent),
                    center = center,
                    radius = gradientCircleRadium
                ),
                radius = gradientCircleRadium,
            )

            // Black outer circle
            drawCircle(
                color = Color.Black, radius = w.times(.28f)
            )

            // Inner orange circle
            drawCircle(
                color = orange, radius = w.times(.25f)
            )

            // Black outlined circle
            drawCircle(
                color = Color.Black, radius = w.times(.16f), style = Stroke(width = 6f)
            )

            // Core black circle
            drawCircle(
                color = Color.Black, radius = w.times(.05f)
            )

            val arc = Path().apply {
                val r = -w.times(.16f) + 2
                moveTo(r, 23f)
                relativeQuadraticBezierTo(
                    dx1 = -50f,
                    dy1 = 10f,
                    dx2 = -66.1f,
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

            // All 3 beads
            translate(
                left = center.x,
                top = center.y,
            ) {
                repeat(3) {
                    rotate(
                        degrees = (120 * it).toFloat(),
                        pivot = Offset.Zero
                    ) {

                        drawCircle(
                            color = Color.Black.copy(1 - animatedApha),
                            radius = w.times(.030f),
                            center = Offset(-w.times(.16f), 0f)
                        )

                        drawPath(
                            path = arc,
                            color = Color.Black.copy(1 - animatedApha),
                        )

                    }
                }
            }

            // Ninja Arc
            val radius = -w.times(.24f)

            val ninjaStarPath = Path().apply {

                val xInitial = -40f
                val yInitial = -60f

                moveTo(xInitial, yInitial)
                lineTo(
                    x = 20f,
                    y = 14f
                )

                cubicTo(
                    x1 = -50f,
                    y1 = 120f,
                    x2 = -w.times(.12f) - 50,
                    y2 = 110f,
                    x3 = radius,
                    y3 = 53f
                )

                cubicTo(
                    x1 = -w.times(.12f) - 90,
                    y1 = 75f,
                    x2 = -w.times(.12f) + 76,
                    y2 = 70f,
                    x3 = xInitial,
                    y3 = yInitial
                )

                close()
            }

            translate(
                left = center.x,
                top = center.y,
            ) {

                rotate(
                    degrees = animatedAngle + 30,
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
                                    color = Color.Black.copy(alpha = animatedApha),
                                )

                            }
                        }

                        drawCircle(
                            center = Offset.Zero, color = orange.copy(alpha = animatedApha), radius = 30f
                        )

                    }
                }
            }

        }
    }

}

@Preview
@Composable
fun ComposablePreview() {
    Sharingan()
}