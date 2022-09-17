package com.acode.projectlauncher

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.Animatable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.acode.installedapps.viewModels.ViewModelApps
import com.acode.projectlauncher.networkSpeed.ViewModelNetworkSpeed
import com.acode.projectlauncher.trialComposables.*
import com.acode.projectlauncher.ui.theme.ProjectLauncherTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectLauncherTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent) {

                    val context = LocalContext.current

                    val viewModelApps: ViewModelApps by viewModels()
                    val viewModelNetworkSpeed: ViewModelNetworkSpeed by viewModels()
                    
                    var showAppDrawer by remember{ mutableStateOf(false)}
                    var showCameraPopUp by remember{ mutableStateOf(false)}

                    Log.d("Speed","d: ${viewModelNetworkSpeed.downloadSpeed.observeAsState().value}")
                    Log.d("Speed","d: ${viewModelNetworkSpeed.uploadSpeed.observeAsState().value}")


//                    CameraPopUpApp(viewModelApps = viewModelApps)
//                    BackHandler() {
//                        showAppDrawer = false
//                    }

                    //SideBar()
                    //ComposeCalender()
                    //SideBarMain()
                    //Drawer()
                    //DrawerHandel()
                    //DrawerHandelFigma()
                    MyApp()


                }
            }
        }
    }
}




