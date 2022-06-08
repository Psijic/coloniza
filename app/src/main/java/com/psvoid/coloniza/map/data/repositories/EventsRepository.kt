package com.psvoid.coloniza.map.data.repositories

import com.psvoid.coloniza.map.data.database.EventsDao
import com.psvoid.coloniza.map.data.sources.FirebaseSource
import com.psvoid.coloniza.map.domain.EventFilter
import com.psvoid.coloniza.map.domain.EventItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class EventsRepository(private val eventsDao: EventsDao) {
    private val firebaseSource = FirebaseSource()

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    suspend fun getAllMarkers(): List<EventItem> = eventsDao.getAllMarkers()
    suspend fun getMarkersByCountry(countryCode: String): List<EventItem> = eventsDao.getMarkersByCountry(countryCode)

    suspend fun insert(marker: EventItem) = eventsDao.insert(marker)
    suspend fun insert(markers: List<EventItem>) = eventsDao.insert(markers)

    @ExperimentalCoroutinesApi
    suspend fun fetch(countryName: String, period: EventFilter.Period): List<EventItem>? =
        firebaseSource.fetchEvents(countryName, period)

    @ExperimentalCoroutinesApi
    fun fetchFlow(countryName: String, period: EventFilter.Period): Flow<List<EventItem>?> =
        firebaseSource.fetchEventsFlow(countryName, period)
}