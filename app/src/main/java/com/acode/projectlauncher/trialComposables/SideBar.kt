package com.acode.projectlauncher.trialComposables

import android.os.Build
import android.util.Range
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.YearMonth
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SideBarMain() {

    Box(modifier = Modifier.padding()) {
        SideBar()
        Box(modifier = Modifier.padding(start = 10.dp, top = 40.dp)) {
            ComposeCalender()
        }
    }

}


@Composable
fun SideBar() {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.CenterStart
    ) {
        val path = Path()
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 50.dp),
            onDraw = {

                val canvasWidth = size.width
                val canvasHeight = size.height

                path.apply {
                    lineTo(
                        x = (canvasWidth / 8),
                        y = 0f
                    )
                    lineTo(
                        x = (canvasWidth / 8),
                        y = canvasHeight - ((canvasWidth / 8) * 2)
                    )
                    arcTo(
                        rect = Rect(
//                            top = (canvasWidth/8) * 2,
//                            left = canvasHeight - canvasWidth/8,
//                            right = (canvasWidth /8) * 2,
//                            bottom = canvasHeight - canvasWidth/8
                            topLeft = Offset(x = canvasWidth / 8,
                                y = (canvasHeight - ((canvasWidth / 8) * 2))),
                            bottomRight = Offset(x = (canvasWidth / 8) * 2.5f,
                                y = (canvasHeight - (canvasWidth / 8)))

                        ),
                        startAngleDegrees = 180f,
                        sweepAngleDegrees = -90f,
                        forceMoveTo = false
                    )
                    lineTo(
                        x = canvasWidth / 2 - 50f,
                        y = canvasHeight - (canvasWidth / 8)
                    )
                    cubicTo(
                        x1 = canvasWidth / 2 - 35f,
                        y1 = canvasHeight - (canvasWidth / 8) ,
                        x2 =canvasWidth/2 - 5,
                        y2 = canvasHeight - (canvasWidth / 8) ,
                        x3 =canvasWidth/2,
                        y3 =(canvasHeight - (canvasWidth / 8) + 50)
                    )
                    lineTo(
                        x = canvasWidth / 2,
                        y = canvasHeight
                    )
                    lineTo(
                        x = 0f,
                        y = canvasHeight
                    )
                    lineTo(
                        x = 0f,
                        y = 0f
                    )


                }
                drawPath(
                    path = path,
                    color = Color.Gray,
                    //style = Stroke(5f)
                )
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ComposeCalender(modifier: Modifier = Modifier, fontSize: TextUnit = 12.5.sp) {
    val mCalendar = Calendar.getInstance()

    val year = mCalendar.get(Calendar.YEAR)
    val day = mCalendar.get(Calendar.DAY_OF_MONTH)

    // Add 1 to get the current month
    val month: Int = mCalendar.get(Calendar.MONTH) + 1

    // Get year month
    val yearMonth = YearMonth.of(year, month)
    //Get length of month
    val daysInMonth: Int = yearMonth.lengthOfMonth()

    val range = Range<Int>(1, daysInMonth)

    Column {
        for (date in 1..daysInMonth) {
            Card(
                modifier = Modifier.wrapContentSize(),
                elevation = 0.dp,
                shape = CircleShape,
                backgroundColor = if (date == day) Color.Gray else Color.Transparent)
            {

                Text(
                    text = date.toString(),
                    color = if (date == day) Color.White else Color.Black,
                    fontSize = fontSize
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewSideBar() {
    SideBar()
}