package com.psvoid.coloniza.city.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.psvoid.coloniza.city.presentation.views.BuildingDto
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

    private var buildingsArr = arrayOf(
        arrayOf(BuildingDto(), BuildingDto(), BuildingDto()),
        arrayOf(BuildingDto(), BuildingDto(), BuildingDto()),
        arrayOf(BuildingDto(), BuildingDto(), BuildingDto()),
    )

    private var _buildings = MutableStateFlow(buildingsArr)
    val buildings: StateFlow<Array<Array<BuildingDto>>> = _buildings

    fun setBuilding(x: Int, y: Int, value: BuildingDto) {
        _buildings.value[x][y] = value
    }

}