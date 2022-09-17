package com.acode.projectlauncher.networkSpeed

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@RequiresApi(Build.VERSION_CODES.M)
class ViewModelNetworkSpeed(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>()

    private lateinit var connectivityManager: ConnectivityManager


//    private var _downloadSpeed = MutableStateFlow(0)
//    val downloadSpeed = _downloadSpeed.asStateFlow()
//
//    private var _uploadSpeed = MutableStateFlow(0)
//    val uploadSpeed = _uploadSpeed.asStateFlow()


    private var _downloadSpeed = MutableLiveData(0)
    val downloadSpeed: LiveData<Int> = _downloadSpeed

    private var _uploadSpeed = MutableLiveData(0)
    val uploadSpeed: LiveData<Int> = _uploadSpeed

    init {

        getInternetSpeed(context)


    }


    private fun getInternetSpeed(context: Context) {
        connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //Network capability of active network
        val nc = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)


        //Download Speed in MBPS
        val downloadSpeed = nc!!.linkDownstreamBandwidthKbps

        //uploadSpeed
        val uploadSpeed = nc.linkUpstreamBandwidthKbps

        _downloadSpeed.value = downloadSpeed

        _uploadSpeed.value = uploadSpeed
    }
}