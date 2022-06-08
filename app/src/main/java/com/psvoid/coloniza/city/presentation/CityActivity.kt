package com.psvoid.coloniza.city.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.ExperimentalComposeApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.psvoid.coloniza.common.presentation.ui.theme.MainTheme
import com.psvoid.coloniza.city.presentation.viewmodels.CityViewModel
import com.psvoid.coloniza.city.presentation.views.CityScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalPermissionsApi
@ExperimentalComposeApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
class CityActivity : ComponentActivity() {
    private val viewModel: CityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
//        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
//            ProvideWindowInsets {
                MainTheme {
                    CityScreen(viewModel)
                }
//            }
        }
        //setContentView(R.layout.activity_main)
    }
}