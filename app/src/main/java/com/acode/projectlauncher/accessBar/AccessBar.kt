package com.acode.projectlauncher.accessBar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.core.graphics.rotationMatrix
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import com.acode.installedapps.viewModels.ViewModelApps

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AccessBar(
    modifier: Modifier = Modifier,
    viewModelApps: ViewModelApps,
    onClickCircle:() -> Unit
) {

    val appList = viewModelApps.listOfAppsB.collectAsState().value

    val screenDimension = LocalConfiguration.current
    val screenWidth = screenDimension.screenWidthDp
    val screenHeight = screenDimension.screenHeightDp

    Box (
        modifier = Modifier
            .padding(10.dp)
            .padding(bottom = 10.dp),
        contentAlignment = Alignment.BottomEnd
            ){
        Column {
            Card(
                modifier = Modifier
                    .align(Alignment.End)
                    .size(width = (screenWidth.div(8).dp), height = (screenHeight.div(3).dp)),
                shape = RoundedCornerShape(30.dp),
                backgroundColor = Color.Gray
            ) {
                LazyColumn(
                    modifier = Modifier
                        .graphicsLayer(alpha = .99f)
                        .drawWithContent {
                            val colors = listOf(Color.Transparent, Color.Black)
                            drawContent()
                            drawRect(
                                brush = Brush.verticalGradient(colors = colors),
                                blendMode = BlendMode.DstIn)

                        },
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    items(appList) {
                        Spacer(modifier = Modifier.size(10.dp))
                        Image(
                            modifier = Modifier
                                .size(((screenWidth.div(8)) - 10).dp)
                                .clip(shape = CircleShape),
                            painter = rememberAsyncImagePainter(model = it.icon),
                            contentDescription = it.name
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            
            Card(
                modifier = Modifier
                    .clickable { onClickCircle() }
                    .size(80.dp),
                backgroundColor = Color.Gray,
                shape = CircleShape
            ) {
                Icon(
                    modifier = Modifier
                        .padding(19.dp),
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu Icon",
                    tint = Color.White
                )
            }
        }
    }
}