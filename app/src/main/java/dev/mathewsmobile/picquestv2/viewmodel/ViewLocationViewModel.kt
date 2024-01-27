package dev.mathewsmobile.picquestv2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mathewsmobile.picquestv2.data.LocationRepository
import dev.mathewsmobile.picquestv2.model.Location
import dev.mathewsmobile.picquestv2.model.Tag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewLocationViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
): ViewModel() {

    private val _locationState = MutableStateFlow<Location?>(null)
    val location = _locationState.asStateFlow()

    fun setLocation(locationId: Int) {
        viewModelScope.launch {
            val location = locationRepository.getLocation(locationId)
            _locationState.emit(location)
        }
    }
}
