package com.psvoid.coloniza.world.city.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.psvoid.coloniza.world.city.domain.BuildingsMap
import com.psvoid.coloniza.world.city.domain.City
import com.psvoid.coloniza.world.city.presentation.views.BuildingDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
class CityViewModel : ViewModel() {
    val city = City()

    private var _isUiVisible = MutableStateFlow(true)
    val isUiVisible: StateFlow<Boolean> = _isUiVisible

    fun setIsUiVisible(value: Boolean) {
        _isUiVisible.value = value
    }

    fun toggleIsUiVisible() {
        _isUiVisible.value = !_isUiVisible.value
    }

    private var _buildings: MutableStateFlow<BuildingsMap> = MutableStateFlow(city.buildings)
    val buildings: StateFlow<BuildingsMap> = _buildings

    fun setBuilding(x: Int, y: Int, value: BuildingDto) {
        _buildings.value[x][y] = value
    }

    init {


    }

}