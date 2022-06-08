package com.psvoid.coloniza.map.presentation.views.compose

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.psvoid.coloniza.R
import com.psvoid.coloniza.common.presentation.ui.theme.Dimens
import com.psvoid.coloniza.common.presentation.ui.theme.MainTheme
import com.psvoid.coloniza.common.presentation.utils.FusedLocationWrapper
import com.psvoid.coloniza.map.presentation.ui.MapNavActions
import com.psvoid.coloniza.map.presentation.ui.MapNavGraph
import com.psvoid.coloniza.map.presentation.viewmodels.MapViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Preview
@Composable
private fun MapTopBarPreview() {
    MainTheme {
        MapTopBar()
    }
}

@ExperimentalPermissionsApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun MapScreen(
    viewModel: MapViewModel,
    locationWrapper: FusedLocationWrapper
) {
    val selectedEvent by viewModel.selectedEvent.collectAsState()
    val isUiVisible by viewModel.isUiVisible.collectAsState()

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
//            initialValue = if (selectedEvent != null) BottomSheetValue.Expanded else BottomSheetValue.Collapsed
            initialValue = BottomSheetValue.Collapsed
        )
    )

    //Navigation
    val navController = rememberNavController()
    val navActions = remember(navController) { MapNavActions(navController) }

    BottomSheetScaffold(
        modifier = Modifier.navigationBarsWithImePadding(),
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = if (selectedEvent != null) Dimens.bottomSheetPeekHeight else Dimens.bottomSheetNoHeight,
        sheetBackgroundColor = MaterialTheme.colorScheme.background,
        sheetContent = { selectedEvent?.let { EventView(it) } },
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
                locationWrapper = locationWrapper
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
            IconButton(onClick = {
                result.value = " Play icon clicked"
            }) {
                Icon(painterResource(R.drawable.ic_domain_24dp), contentDescription = "")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Filled.DateRange, contentDescription = "")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Search, contentDescription = "")
            }
            IconButton(onClick = {}) {
                Icon(painterResource(R.drawable.ic_list_alt_24), contentDescription = "")
            }
        },
    )
}