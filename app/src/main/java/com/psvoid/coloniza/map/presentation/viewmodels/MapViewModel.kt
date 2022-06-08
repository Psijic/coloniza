package com.psvoid.coloniza.map.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.psvoid.coloniza.common.domain.utils.LoadingStatus
import com.psvoid.coloniza.map.data.database.AppDatabase
import com.psvoid.coloniza.map.data.database.CountryData
import com.psvoid.coloniza.map.data.network.Config
import com.psvoid.coloniza.map.data.network.Config.today
import com.psvoid.coloniza.map.data.repositories.CountriesRepository
import com.psvoid.coloniza.map.data.repositories.EventsRepository
import com.psvoid.coloniza.map.domain.EventFilter
import com.psvoid.coloniza.map.domain.EventItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.collections.set

typealias EventsMap = MutableMap<String, List<EventItem>>

@ExperimentalCoroutinesApi
class MapViewModel(application: Application) : AndroidViewModel(application) {
    private val _location = MutableStateFlow(LatLng(Config.MAP_DEFAULT_LATITUDE, Config.MAP_DEFAULT_LONGITUDE))
    val location: StateFlow<LatLng> = _location
    fun setLocation(value: LatLng) {
        _location.value = value
    }

    private val allEvents: EventsMap = mutableMapOf()

    private val _currentEvents = MutableStateFlow<MutableList<EventItem>>(mutableListOf())
    val currentEvents: StateFlow<List<EventItem>> = _currentEvents

//    private val _isBottomSheetOpened = MutableStateFlow(BottomSheetValue.Collapsed)
//    val isBottomSheetOpened: StateFlow<BottomSheetValue> = _isBottomSheetOpened

    val selectedEvent = MutableStateFlow<EventItem?>(null)

    private var _isUiVisible = MutableStateFlow(true)
    val isUiVisible: StateFlow<Boolean> = _isUiVisible

    fun setIsUiVisible(value: Boolean) {
        _isUiVisible.value = value
    }

    fun toggleIsUiVisible() {
        _isUiVisible.value = !_isUiVisible.value
    }

//    val algorithm = NonHierarchicalViewBasedAlgorithm<EventItem>(0, 0)

//    val selectedEventLd = MutableLiveData<EventItem>()
//    var isHideUI = MutableLiveData(false)
//    var bottomSheetState = BottomSheetBehavior.STATE_HIDDEN // TODO: save height expanded?

    /** The internal MutableLiveData that stores the status of the most recent request */
    private val _clusterStatus = MutableLiveData<LoadingStatus>()
    val clusterStatus: LiveData<LoadingStatus>
        get() = _clusterStatus

    private val markerRepo: EventsRepository
    private val countriesRepo: CountriesRepository


//    private val markersObserver = { markers: List<ClusterMarker> -> getMarkers(markers) }

    // Events period
    private val _period = MutableLiveData(Config.period)
    val period: LiveData<EventFilter.Period>
        get() = _period

    init {
        val eventsDao = AppDatabase.getInstance(application).eventsDao
        markerRepo = EventsRepository(eventsDao)
        val countriesDao = AppDatabase.getInstance(application).countriesDao
        countriesRepo = CountriesRepository(countriesDao)

        updateMarkers()
    }

    /** Here we set the period of shown cached markers */
    fun setPeriod(value: EventFilter.Period) {
        _period.value = value
//        algorithm.clearItems()
        _clusterStatus.value = LoadingStatus.DONE

        allEvents.forEach {
            // Pass events if they are in the actual date range
            val countryEvents = it.value.filter { event ->
                when (period.value) {
                    EventFilter.Period.FUTURE -> event.startDt.value.nano > System.currentTimeMillis()
                    EventFilter.Period.TODAY -> event.startDt.value.dayOfYear == today.dayOfYear
                    else -> true
                }
            }

            addClusterItems(countryEvents)
        }
    }

    /** Check if Android database have actual markers for current countries. If not, fetch them. */
    private fun updateMarkers() {
        viewModelScope.launch(Dispatchers.IO) {
            for (countryName in Config.countries) {
                val markers = markerRepo.getMarkersByCountry(countryName)
                allEvents[countryName] = markers
                val countryData = countriesRepo.getByCountry(countryName)
                val timestamp = countryData?.timestamp ?: 0
                // Check if the database data is cleared/broken or outdated
                Timber.i("Getting database data, country: $countryName, timestamp: $timestamp")
                if (markers.isNullOrEmpty() || timestamp < Config.launchTime - Config.cacheRefreshTime) {
                    Timber.d("Fetching markers from Firebase")
                    period.value?.let { fetchMarkers(countryName, it) }
                    //                    markers = markerRepo.fetchFirebase(countryName, period)
                } else {
                    Timber.d("Add markers from cache")
                    addClusterItems(markers)
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    private suspend fun fetchMarkers(countryName: String, period: EventFilter.Period) {
        _clusterStatus.postValue(LoadingStatus.LOADING)

        val markers = markerRepo.fetch(countryName, period)
        if (markers.isNullOrEmpty()) {
            // Add cached markers if Firebase doesn't work well and returns an empty list.
            addClusterItems(allEvents[countryName])
        } else {
            allEvents[countryName] = markers
            addClusterItems(markers)
            saveMarkers(countryName, markers)
        }
    }

    private fun saveMarkers(countryName: String, markers: List<EventItem>) {
        Timber.i("Saving $countryName markers into DB")
        insertMarkers(markers)
        insertCountry(CountryData(countryName))
    }

    /** Launching a new coroutine to insert the data in a non-blocking way */
    private fun insertMarkers(markers: List<EventItem>) =
        viewModelScope.launch(Dispatchers.IO) { markerRepo.insert(markers) }

    private fun insertCountries(countries: List<CountryData>) =
        viewModelScope.launch(Dispatchers.IO) { countriesRepo.insert(countries) }

    private fun insertCountry(country: CountryData) =
        viewModelScope.launch(Dispatchers.IO) { countriesRepo.insert(country) }

    private fun addClusterItems(items: List<EventItem>?) {
        if (items.isNullOrEmpty()) {
            _clusterStatus.postValue(LoadingStatus.ERROR)
        } else {
//            algorithm.addItems(items)
            _currentEvents.value.addAll(items)
            _clusterStatus.postValue(LoadingStatus.DONE)
        }
    }


}