package com.acode.projectlauncher.accessbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties

@Composable
fun MainContent() {


    AccessBar()
}

@Composable
fun AccessBar() {
    val list = listOf("A", "B", "C")
    var currentCoordinates by remember { mutableStateOf(IntOffset(0, 0)) }

    val popUpPositionProvider = object : PopupPositionProvider {
        override fun calculatePosition(
            anchorBounds: IntRect,
            windowSize: IntSize,
            layoutDirection: LayoutDirection,
            popupContentSize: IntSize,
        ): IntOffset {
            return currentCoordinates.copy(
                x = (windowSize.width - popupContentSize.width) / 2,
                y = currentCoordinates.y + popupContentSize.height / 2
            )
        }
    }

    Card(
        modifier = Modifier
            .wrapContentSize()
            .size(width = 40.dp, height = 150.dp),
        border = BorderStroke(width = 2.dp, color = Color.Black),
        backgroundColor = Color.Gray
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            list.forEach {
                val isPopUpVisible = remember {
                    mutableStateOf(false)
                }
                AccessBarItem(
                    modifier = Modifier
                        .onGloballyPositioned { layoutCoordinates: LayoutCoordinates ->
                            val (x: Int, y: Int) = when {
                                layoutCoordinates.isAttached -> with(layoutCoordinates.positionInRoot()) {
                                    x.toInt() to y.toInt()
                                }
                                else -> 0 to 0
                            }
                            currentCoordinates = IntOffset(x, y)
                        },
                    text = it,
                    onClick = { isPopUpVisible.value = !isPopUpVisible.value }
                )


                AnimatedVisibility(isPopUpVisible.value) {
                    PopUpItem(
                        popupPositionProvider = popUpPositionProvider,
                        offset = currentCoordinates,
                        text = it + "Letter",
                        onDismissRequest = {
                            isPopUpVisible.value = !isPopUpVisible.value
                        },

                        )
                }
                Spacer(modifier = Modifier.size(3.dp))
            }
        }
    }
}

@Composable
fun PopUpItem(
    text: String,
    offset: IntOffset,
    onDismissRequest: () -> Unit,
    popupPositionProvider: PopupPositionProvider,
) {
    Popup(
        offset = offset,
        onDismissRequest = onDismissRequest,
        properties = PopupProperties()) {

        Card(Modifier.wrapContentWidth()) {
            Text(text = text)
        }
    }
}

@Composable
fun AccessBarItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(30.dp)
            .background(Color.White)
            .border(width = 2.dp, shape = RoundedCornerShape(5.dp), color = Color.White),
        contentAlignment = Alignment.Center
        ) {

        Text(
            modifier = Modifier
                .clickable { onClick() },
            text = text)
    }
}