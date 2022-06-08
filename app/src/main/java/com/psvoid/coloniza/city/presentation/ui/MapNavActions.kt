package com.psvoid.coloniza.city.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.psvoid.coloniza.city.presentation.ui.MapDestinations.MAP
import com.psvoid.coloniza.city.presentation.viewmodels.CityViewModel
import com.psvoid.coloniza.city.presentation.views.CityView
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MapNavActions(navController: NavHostController) {
    val navigateToList: () -> Unit = {
//        navController.navigate(LIST)
    }
}

object MapDestinations {
    const val MAP = "map"
//    const val LIST = "list"
}

@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@ExperimentalMaterial3Api
@Composable
fun MapNavGraph(
    navController: NavHostController,
    viewModel: CityViewModel,
    navActions: MapNavActions,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MAP,
        modifier = modifier,
    ) {
        composable(MAP) {
            CityView(viewModel = viewModel)
        }
    }
}