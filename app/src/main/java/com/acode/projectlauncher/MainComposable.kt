package com.acode.projectlauncher

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.acode.projectlauncher.trialComposables.Drawer
import com.acode.projectlauncher.trialComposables.SideBar
import com.acode.projectlauncher.trialComposables.SideBarMain

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyApp(){
    val context = LocalContext.current

    val screenDimension = LocalConfiguration.current
    val screenWidth = screenDimension.screenWidthDp

    Box(modifier = Modifier.fillMaxSize()){

        Drawer(modifier = Modifier
            .padding(top = 50.dp, start = 37.dp)
            .clickable {
                Toast
                    .makeText(context, "Drawer Clicked", Toast.LENGTH_LONG)
                    .show()
            }
        )
        SideBarMain()

    }
}