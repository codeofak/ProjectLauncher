package com.acode.projectlauncher.trialComposables

import android.graphics.Matrix
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.core.graphics.PathParser
import androidx.core.graphics.PathParser.createPathFromPathData

@Composable
fun Drawer(modifier: Modifier = Modifier) {

    val screenDimensions = LocalConfiguration.current

    val screenWidth = screenDimensions.screenWidthDp
    val screenHeight = screenDimensions.screenHeightDp

    Box(modifier = modifier.wrapContentSize()) {
        Row(modifier = Modifier) {
            Card(
                modifier = Modifier.size(width = (screenWidth / 2).dp, height = (screenHeight / 6).dp),
                elevation = 0.dp,
                backgroundColor = Color.DarkGray,
                shape = RectangleShape
            ) {

            }
            DrawerHandelFigma(modifier = Modifier.height((screenHeight/6).dp))
        }
    }
}

@Composable
fun DrawerHandel() {
    Box(modifier = Modifier.fillMaxSize()) {

        val path = Path()

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)             ,
            onDraw = {

                val canvasWidth = size.width
                val canvasHeight = size.height

                path.apply {

                    lineTo(
                        x = canvasWidth/18,
                        y = 0f
                    )
                    cubicTo(
                        x1 = canvasWidth/1.7f,
                        y1 = 0f,
                        x2 = canvasWidth/20,
                        y2 = (canvasHeight/15) * 5,
                        x3 = canvasWidth/2,
                        y3 = canvasHeight/3
                    )
                    cubicTo(
                        x1 = (canvasWidth/2)  + 100,
                        y1 = (canvasHeight/3) ,
                        x2 = (canvasWidth/2) + 100f,
                        y2 = (canvasHeight/3) + 50f,
                        x3 = (canvasWidth/2) + 100f,
                        y3 = (canvasHeight/2)

                    )

                }
                drawPath(
                    path = path,
                    color = Color.Black,
                    style = Stroke(4f)
                )
            }
        )
    }
}

@Composable
fun DrawerHandelFigma(modifier: Modifier = Modifier){


    Canvas(
        modifier = modifier
            .width(135.dp)
            .height(800.dp)
        //.fillMaxWidth()
        //.aspectRatio(1f)

    ) {
        val fillPath = PathParser.createPathFromPathData("M 81.90074920654297 794.095947265625 C 78.85239553451538 798.203962802887 70.8974609375 800 70.8974609375 800 L 0 800 L 0.0000038230982681852765 399.50677490234375 L 134.98876953125 399.50677490234375 L 134.98876953125 571.186279296875 C 134.98876953125 571.186279296875 135.25672578811646 577.0179781913757 133.17379760742188 579.5811157226562 C 130.44581270217896 582.9380249977112 122.51081848144531 582.533203125 122.51081848144531 582.533203125 L 97.5549087524414 582.533203125 C 97.5549087524414 582.533203125 90.17233109474182 582.555823802948 87.7994155883789 584.9932250976562 C 85.05646896362305 587.8107135295868 85.07695770263672 596.1248779296875 85.07695770263672 596.1248779296875 L 85.07695770263672 781.7957763671875 C 85.07695770263672 781.7957763671875 84.15040564537048 791.0642704963684 81.90074920654297 794.095947265625 Z M 82.01744842529297 5.918619155883789 C 78.97579336166382 1.800485610961914 71.03833770751953 0 71.03833770751953 0 L 0 0 L 0.0000038230982681852765 399.50677490234375 L 134.98876953125 399.50677490234375 L 134.98880004882812 229.37730407714844 C 134.98880004882812 229.37730407714844 135.25617694854736 223.53122758865356 133.17782592773438 220.96177673339844 C 130.4558355808258 217.59659910202026 122.53827667236328 218.00245666503906 122.53827667236328 218.00245666503906 L 97.63720703125 218.00245666503906 C 97.63720703125 218.00245666503906 90.27085375785828 217.9797818660736 87.90315246582031 215.536376953125 C 85.16623330116272 212.71194911003113 85.18667602539062 204.37730407714844 85.18667602539062 204.37730407714844 L 85.18667602539062 18.249069213867188 C 85.18667602539062 18.249069213867188 84.26216149330139 8.957763195037842 82.01744842529297 5.918619155883789 Z ")
        //fillPath.fillType = Path.FillType.EVEN_ODD
        val rectF = RectF()
        fillPath.computeBounds(rectF, true)
        val matrix = Matrix()
        val scale = minOf( size.width / rectF.width(), size.height / rectF.height() )
        matrix.setScale(scale, scale)
        fillPath.transform(matrix)
        val composePathFill = fillPath.asComposePath()

//        drawPath(path = composePathFill, color = Color(red = 0.8509804010391235f, green = 0.8509804010391235f, blue = 0.8509804010391235f, alpha = 1f), style = Fill)
//        drawPath(path = composePathFill, color = Color.Transparent, style = Stroke(width = 3f, miter = 4f, join = StrokeJoin.Round))
        //drawPath(path = composePathFill,color = Color.Green, style = Fill)
        drawPath(path = composePathFill, color = Color.Gray, style = Fill)
    }
}