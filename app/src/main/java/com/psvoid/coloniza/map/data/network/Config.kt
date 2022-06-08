package com.psvoid.coloniza.map.data.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.psvoid.coloniza.map.domain.EventFilter
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

object Config {
    @RequiresApi(Build.VERSION_CODES.O)
    val today: LocalDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)
    val launchTime = System.currentTimeMillis()
    const val cacheRefreshTime = 86400000 // 24 hours

    const val animateCameraDuration = 300
    const val mapStyle = -1 //R.raw.map_style
    const val showMarkerImages = false
    const val logs = true
    const val defaultSearchRadius = 15f
    const val searchRadius = 0.035f // in miles = 0.022f
    const val minMapZoom = 2f //11f
    const val maxMapZoom = 21f
    val period = EventFilter.Period.FUTURE
    const val pageSize = 30
    const val MAP_DEFAULT_LATITUDE = 52.520008
    const val MAP_DEFAULT_LONGITUDE = 13.404954

    val countries = mutableListOf<String>()
}