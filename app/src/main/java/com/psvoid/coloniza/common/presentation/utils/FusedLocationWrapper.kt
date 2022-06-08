package com.psvoid.coloniza.common.presentation.utils

import android.Manifest
import android.location.Location
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resumeWithException

@ExperimentalCoroutinesApi
class FusedLocationWrapper(private val fusedLocation: FusedLocationProviderClient) {
    @RequiresPermission(anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION])
    fun lastLocation(): Flow<Location> = flow {
        fusedLocation.lastLocation.await()?.let { location ->
            emit(location)
        }
    }

    @RequiresPermission(anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION])
    suspend fun awaitLastLocation(): Location =
        suspendCancellableCoroutine { continuation ->
            fusedLocation.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    continuation.resume(location, onCancellation = {})
                }
            }.addOnFailureListener { e ->
                continuation.resumeWithException(e)
            }
        }

}

@ExperimentalCoroutinesApi
fun ComponentActivity.fusedLocationWrapper() =
    FusedLocationWrapper(LocationServices.getFusedLocationProviderClient(this))

