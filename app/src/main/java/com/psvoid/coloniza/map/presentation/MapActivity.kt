package com.psvoid.coloniza.map.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.ExperimentalComposeApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.psvoid.coloniza.common.presentation.ui.theme.MainTheme
import com.psvoid.coloniza.common.presentation.utils.fusedLocationWrapper
import com.psvoid.coloniza.map.presentation.viewmodels.MapViewModel
import com.psvoid.coloniza.map.presentation.views.compose.MapScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalPermissionsApi
@ExperimentalComposeApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
class MapActivity : ComponentActivity() {
    private val viewModel: MapViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
//        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
//            ProvideWindowInsets {
                MainTheme {
                    MapScreen(viewModel, locationWrapper = fusedLocationWrapper())
                }
//            }
        }
        //setContentView(R.layout.activity_main)
    }
}