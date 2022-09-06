package com.acode.projectlauncher.appsDetail


import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import coil.compose.rememberAsyncImagePainter
import com.acode.installedapps.dataClass.AppClass
import com.acode.installedapps.viewModels.ViewModelApps

@Composable
fun MainContent(viewModelApps: ViewModelApps) {


    AppDrawerGrid(viewModelApps = viewModelApps)
}

@Composable
fun AppDrawerGrid(viewModelApps: ViewModelApps) {

    val density = LocalDensity.current
    val listOfApps = viewModelApps.listOfAppsB.collectAsState().value
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
        //modifier = Modifier
        //.wrapContentSize()
        //.size(width = 40.dp, height = 150.dp),
        border = BorderStroke(width = 2.dp, color = Color.Black),
        backgroundColor = Color.Gray
    ) {
//        LazyColumn {
//
//            items(listOfApps) {
//                val isPopUpVisible = remember {
//                    mutableStateOf(false)
//                }
//                AccessBarItem(
//                    modifier = Modifier
//                        .onGloballyPositioned { layoutCoordinates: LayoutCoordinates ->
//                            val (x: Int, y: Int) = when {
//                                layoutCoordinates.isAttached -> with(layoutCoordinates.positionInRoot()) {
//                                    x.toInt() to y.toInt()
//                                }
//                                else -> 0 to 0
//                            }
//                            currentCoordinates = IntOffset(x, y)
//                        },
//                    text = it.name,
//                    onClick = { isPopUpVisible.value = !isPopUpVisible.value }
//                )
//
//
//                AnimatedVisibility(isPopUpVisible.value) {
//                    PopUpItem(
//                        popupPositionProvider = popUpPositionProvider,
//                        offset = currentCoordinates,
//                        text = it.name,
//                        onDismissRequest = {
//                            isPopUpVisible.value = !isPopUpVisible.value
//                        },
//
//                        )
//                }
//                Spacer(modifier = Modifier.size(3.dp))
//            }
//        }

        //GRID
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 64.dp)
        ){
            items(listOfApps){
                val isPopUpVisible = remember {
                    mutableStateOf(false)
                }
                AccessBarItemGrid(app = it, onClick = {isPopUpVisible.value = !isPopUpVisible.value})
                AnimatedVisibility(
                    isPopUpVisible.value,
                    enter = slideInVertically {
                        // Slide in from 40 dp from the top.
                        with(density) { -40.dp.roundToPx() }
                    } + expandVertically(
                        // Expand from the top.
                        expandFrom = Alignment.Top
                    ) + fadeIn(
                        // Fade in with the initial alpha of 0.3f.
                        initialAlpha = 0.3f
                    ),
                    exit = slideOutVertically() + shrinkVertically() + fadeOut()
                ) {
                    PopUpItem(
                        popupPositionProvider = popUpPositionProvider,
                        offset = currentCoordinates,
                        text = it.name,
                        onDismissRequest = {
                            isPopUpVisible.value = !isPopUpVisible.value
                        },

                        )
                }
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

        Card(Modifier.size(200.dp)) {
            Text(text = text)
        }
    }
}

@Composable
fun AccessBarItemGrid(app: AppClass,onClick:() -> Unit) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable { onClick() }
            //.size(width = 200.dp, height = 220.dp)
    ) {
        Column(Modifier.padding(3.dp)) {
            Image(
                modifier = Modifier.size(40.dp),
                painter = rememberAsyncImagePainter(model = app.icon),
                contentDescription = app.name
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = app.name,
                maxLines = 1,
                fontSize = 13.sp
            )
        }

    }
}


@Composable
fun AccessBarItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Box {

        Text(
            modifier = Modifier
                .clickable { onClick() },
            text = text)
    }
}