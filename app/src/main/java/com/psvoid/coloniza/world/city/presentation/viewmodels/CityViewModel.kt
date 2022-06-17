package com.psvoid.coloniza.world.city.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.psvoid.coloniza.world.city.domain.BuildingsMap
import com.psvoid.coloniza.world.city.domain.City
import com.psvoid.coloniza.world.city.domain.buildings.Building
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
class CityViewModel : ViewModel() {
    private var _isUiVisible = MutableStateFlow(true)
    val isUiVisible: StateFlow<Boolean> = _isUiVisible

    fun setIsUiVisible(value: Boolean) {
        _isUiVisible.value = value
    }

    fun toggleIsUiVisible() {
        _isUiVisible.value = !_isUiVisible.value
    }

    val city = City()

    private var _buildings: MutableStateFlow<BuildingsMap> = MutableStateFlow(city.buildings)
    val buildings: StateFlow<BuildingsMap> = _buildings

    val selectedBuilding = MutableStateFlow<Pair<Int, Int>?>(null)

    fun getSelectedBuilding(): Building? = selectedBuilding.value?.let { getBuilding(it.first, it.second) }

    fun getBuilding(x: Int, y: Int) = _buildings.value[x][y]

    private fun setBuilding(x: Int, y: Int, value: Building) {
        city.buildings[x][y] = value
    }


    /* Build a new building here */
    fun build(building: Building) {
        // Check if there enough resources and all conditions met

        selectedBuilding.value?.let { setBuilding(it.first, it.second, building) }
        selectedBuilding.value = null
    }


}