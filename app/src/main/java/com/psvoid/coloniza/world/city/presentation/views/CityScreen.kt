package com.psvoid.coloniza.world.city.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.psvoid.coloniza.common.presentation.ui.theme.Dimens
import com.psvoid.coloniza.common.presentation.ui.theme.MainTheme
import com.psvoid.coloniza.world.city.presentation.ui.MapNavActions
import com.psvoid.coloniza.world.city.presentation.ui.MapNavGraph
import com.psvoid.coloniza.world.city.presentation.viewmodels.CityViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Preview
@Composable
private fun CityPreview() {
    MainTheme {
        CityScreen(CityViewModel())
    }
}

@ExperimentalPermissionsApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun CityScreen(
    viewModel: CityViewModel,
) {
    val isUiVisible by viewModel.isUiVisible.collectAsState()
    val selectedBuilding by viewModel.selectedBuilding.collectAsState()

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed
        )
    )

    //Navigation
    val navController = rememberNavController()
    val navActions = remember(navController) { MapNavActions(navController) }

    BottomSheetScaffold(
        modifier = Modifier.navigationBarsWithImePadding(),
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = if (selectedBuilding != null) Dimens.bottomSheetPeekHeight else Dimens.bottomSheetNoHeight,
        sheetBackgroundColor = MaterialTheme.colorScheme.background,
        sheetContent = { viewModel.getSelectedBuilding()?.let { BuildingDialog(it, viewModel) } },
        topBar = { if (isUiVisible) MapTopBar() }
    ) {
        Column(
//            modifier = Modifier.fillMaxSize()
        ) {
            MapNavGraph(
                navController = navController,
                viewModel = viewModel,
                navActions = navActions,
                modifier = Modifier,
            )
        }
    }
}


@Composable
fun MapTopBar() {
    val result = remember { mutableStateOf("") }
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        title = {},
        backgroundColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        navigationIcon = {
            // Drawer icon
            IconButton(
                onClick = {
                    result.value = "Drawer icon clicked"
                }
            ) {
                Icon(Icons.Filled.Menu, contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.DateRange, contentDescription = "")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Search, contentDescription = "")
            }
        },
    )
}