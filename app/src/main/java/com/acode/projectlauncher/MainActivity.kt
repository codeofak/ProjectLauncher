package com.acode.projectlauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.acode.composecamera.composables.CameraApp
import com.acode.installedapps.viewModels.ViewModelApps
import com.acode.projectlauncher.accessBar.AccessBar
import com.acode.projectlauncher.appsDetail.AppDrawerGrid
import com.acode.projectlauncher.appsDetail.MainContent
import com.acode.projectlauncher.camera.CameraPopUpApp
import com.acode.projectlauncher.ui.theme.ProjectLauncherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectLauncherTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent) {

                    val viewModelApps: ViewModelApps by viewModels()
                    
                    var showAppDrawer by remember{ mutableStateOf(false)}
                    var showCameraPopUp by remember{ mutableStateOf(false)}



//                    if (showAppDrawer){
//                        AppDrawerGrid(viewModelApps = viewModelApps)
//                    }else{
//                        AccessBar(
//                            viewModelApps = viewModelApps,
//                            onClickCircle = {
//                                showAppDrawer = true
//                            },
//                        )
//                    }

                    CameraPopUpApp(viewModelApps = viewModelApps)
                    BackHandler() {
                        showAppDrawer = false
                    }
                    
                }
            }
        }
    }
}


