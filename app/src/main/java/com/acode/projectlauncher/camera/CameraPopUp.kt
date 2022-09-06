package com.acode.projectlauncher.camera

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import com.acode.composecamera.composables.CameraApp
import com.acode.installedapps.viewModels.ViewModelApps
import com.acode.projectlauncher.accessBar.AccessBar


@Composable
fun CameraPopUpApp(viewModelApps: ViewModelApps) {
    val density = LocalDensity.current
    var currentCoordinates by remember { mutableStateOf(IntOffset(0, 0)) }
    val isCameraPopUpVisible = remember {
        mutableStateOf(false)
    }

    val popUpPositionProvider = object : PopupPositionProvider {
        override fun calculatePosition(
            anchorBounds: IntRect,
            windowSize: IntSize,
            layoutDirection: LayoutDirection,
            popupContentSize: IntSize,
        ): IntOffset {
            return currentCoordinates.copy(
                //x = (windowSize.width - popupContentSize.width) / 2,
                //y = currentCoordinates.y + popupContentSize.height / 2
                x = (windowSize.width - popupContentSize.width) - 200,
                y = (windowSize.height - popupContentSize.height) - 40
            )
        }

    }
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        AccessBar(
            modifier = Modifier
                .onGloballyPositioned { layoutCoordinates: LayoutCoordinates ->
                    val (x: Int, y: Int) = when {
                        layoutCoordinates.isAttached -> with(layoutCoordinates.positionInRoot()) {
                            (x).toInt() to (y).toInt()
                        }
                        else -> 0 to 0
                    }
                    currentCoordinates = IntOffset(x = x, y = y)
                }, viewModelApps = viewModelApps) {
            isCameraPopUpVisible.value = !isCameraPopUpVisible.value
        }
    }
    AnimatedVisibility(isCameraPopUpVisible.value) {
        CameraPopUpItem(
            //offset = currentCoordinates,
            popupPositionProvider = popUpPositionProvider,
            onDismissRequest = { isCameraPopUpVisible.value = false },
        )
    }

}


@Composable
fun CameraPopUpItem(
    //offset: IntOffset,
    onDismissRequest: () -> Unit,
    popupPositionProvider: PopupPositionProvider,
) {
    Popup(
        //offset =
        popupPositionProvider = popupPositionProvider,
        onDismissRequest = onDismissRequest,
        properties = PopupProperties()) {

        Card(Modifier.size(width = 150.dp, height = 300.dp),

        ) {
            CameraApp()
        }
    }
}