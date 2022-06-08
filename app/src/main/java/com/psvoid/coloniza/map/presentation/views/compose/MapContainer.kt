package com.psvoid.coloniza.map.presentation.views.compose

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.psvoid.coloniza.common.presentation.utils.FusedLocationWrapper
import com.psvoid.coloniza.map.data.network.Config
import com.psvoid.coloniza.map.presentation.viewmodels.MapViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import java.time.LocalTime

@RequiresPermission(anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION])
@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun MapView(
    fusedLocationWrapper: FusedLocationWrapper,
    viewModel: MapViewModel
) {
    val currentEvents by viewModel.currentEvents.collectAsState()
    val selectedEvent by viewModel.selectedEvent.collectAsState()

    Timber.v("Start markers update ${LocalTime.now()}")
    val content: (@Composable () -> Unit) = {
        for (event in currentEvents) {
            Marker(
                onClick = {
                    viewModel.selectedEvent.value = event
                    viewModel.setIsUiVisible(true)
                    false
                },
                position = event.getPosition(),
                icon = event.getMarkerDescriptor(selectedEvent == event)
            )
        }
    }
    Timber.v("End markers update ${LocalTime.now()}")

    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )

    if (locationPermissionsState.allPermissionsGranted) {
        LaunchedEffect (fusedLocationWrapper){
            val location = fusedLocationWrapper.awaitLastLocation()
            viewModel.setLocation(LatLng(location.latitude, location.longitude))

        }
    } else SideEffect {
        locationPermissionsState.launchMultiplePermissionRequest()
    }

    MapViewContainer(
        viewModel = viewModel,
        onMapClick = {
            if (selectedEvent == null) viewModel.toggleIsUiVisible()
            viewModel.selectedEvent.value = null
        },
        content = content,
        hasLocationPermission = locationPermissionsState.allPermissionsGranted
    )
}

@ExperimentalCoroutinesApi
@Composable
fun MapViewContainer(
//    cameraPositionState: CameraPositionState,
    viewModel: MapViewModel,
    onMapClick: (LatLng) -> Unit = {},
    onMapLoaded: () -> Unit = {},
    onZoomChanged: (() -> Unit)? = null,
    content: @Composable() (() -> Unit)? = null,
    hasLocationPermission: Boolean = false
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(viewModel.location.value, 10f)
    }

    val mapProperties =
        MapProperties(
            maxZoomPreference = Config.maxMapZoom,
            minZoomPreference = Config.minMapZoom,
            isMyLocationEnabled = hasLocationPermission
        )


    val mapUiSettings by remember { //TODO: make custom location button
        mutableStateOf(
            MapUiSettings(
                myLocationButtonEnabled = true, mapToolbarEnabled = false
            )
        )
    }

    GoogleMap(
        Modifier.fillMaxSize(),
        properties = mapProperties,
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings,
        onMapLoaded = onMapLoaded,
        onMapClick = onMapClick,
        content = content
    )
}