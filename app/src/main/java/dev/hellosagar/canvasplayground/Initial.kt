package dev.hellosagar.canvasplayground

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.withTransform

@Composable
fun Initial() {
    Canvas(modifier = Modifier.fillMaxSize()) {

        val canvasHeight = size.height
        val canvasWidth = size.width

        drawLine(
            start = Offset(x = canvasWidth, y = 0f),
            end = Offset(x = 0f, y = canvasHeight),
            color = Color.Blue,
            strokeWidth = 4f
        )

        drawCircle(
            color = Color.Red,
            radius = size.minDimension / 20,
            center = Offset(x = canvasWidth / 2, y = canvasHeight / 2)
        )

//        drawOval(
//            color = Color.Green,
//            size = Size(width = 100f, height = 0f),
//            alpha = 1f,
//        )


//        inset(50f,30f){
//            drawRect(
//                color = Color.Cyan,
//                size = size/2f
//            )
//        }

//        rotate(45f) {
//            drawRect(
//                color = Color.Cyan,
//                topLeft = Offset(x= canvasWidth/3, canvasHeight/3),
//                size = size / 3f
//            )
//        }

        withTransform({
            translate(left = canvasWidth / 5F)
            rotate(degrees = 45F)
        }) {
            drawRect(
                color = Color.Gray,
                topLeft = Offset(x = canvasWidth / 3F, y = canvasHeight / 3F),
                size = size / 3F
            )
        }
    }
}