package dev.mathewsmobile.picquestv2

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private var _currentLocationState: MutableStateFlow<Location> = MutableStateFlow(Location(DEFAULT_LATITUDE, DEFAULT_LONGITUDE))
    val currentLocation = _currentLocationState.asStateFlow()

    fun updateUserLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _currentLocationState.emit(Location(latitude, longitude))
        }
    }
}