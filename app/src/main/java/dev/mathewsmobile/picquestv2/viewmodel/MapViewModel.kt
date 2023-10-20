package dev.mathewsmobile.picquestv2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor() : ViewModel() {

    companion object {
        const val DEFAULT_LONGITUDE = 44.967243
        const val DEFAULT_LATITUDE = -103.771556
    }

    data class Location(val latitude: Double, val longitude: Double)

    private var _currentLocationState: MutableStateFlow<Location> = MutableStateFlow(
        Location(
            DEFAULT_LATITUDE, DEFAULT_LONGITUDE
        )
    )
    val currentLocation = _currentLocationState.asStateFlow()

    fun updateUserLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _currentLocationState.emit(Location(latitude, longitude))
        }
    }
}