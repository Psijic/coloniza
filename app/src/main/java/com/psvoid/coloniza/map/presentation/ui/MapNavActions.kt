package com.psvoid.coloniza.map.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.psvoid.coloniza.common.presentation.utils.FusedLocationWrapper
import com.psvoid.coloniza.map.presentation.ui.MapDestinations.LIST
import com.psvoid.coloniza.map.presentation.ui.MapDestinations.MAP
import com.psvoid.coloniza.map.presentation.viewmodels.MapViewModel
import com.psvoid.coloniza.map.presentation.views.compose.MapView
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MapNavActions(navController: NavHostController) {
    val navigateToList: () -> Unit = {
        navController.navigate(LIST)
    }
}

object MapDestinations {
    const val MAP = "map"
    const val LIST = "list"
}

@ExperimentalPermissionsApi
@SuppressLint("MissingPermission")
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@ExperimentalMaterial3Api
@Composable
fun MapNavGraph(
    navController: NavHostController,
    viewModel: MapViewModel,
    navActions: MapNavActions,
    locationWrapper: FusedLocationWrapper,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MAP,
        modifier = modifier,
    ) {
        composable(MAP) {
            MapView(fusedLocationWrapper = locationWrapper, viewModel = viewModel)
        }
    }
}