package dev.mathewsmobile.picquestv2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(MapboxExperimental::class)
class MapViewModel @Inject constructor() : ViewModel() {

    companion object {
        const val DEFAULT_LONGITUDE = -84.3877
        const val DEFAULT_LATITUDE = 33.7488
    }

    data class Location(val latitude: Double, val longitude: Double)

    private var _currentLocationState: MutableStateFlow<Location> = MutableStateFlow(
        Location(
            DEFAULT_LATITUDE, DEFAULT_LONGITUDE
        )
    )
    val currentLocation = _currentLocationState.asStateFlow()

    private val _mapViewportState: MutableStateFlow<MapViewportState> = MutableStateFlow(
        MapViewportState().apply {
            setCameraOptions {
                zoom(12.0)
                center(Point.fromLngLat(DEFAULT_LONGITUDE, DEFAULT_LATITUDE))
                pitch(0.0)
                bearing(0.0)
            }
        }
    )
    val mapViewportState = _mapViewportState.asStateFlow()


    fun updateUserLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _currentLocationState.emit(Location(latitude, longitude))
        }
    }
}