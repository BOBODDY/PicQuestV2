package dev.mathewsmobile.picquestv2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mathewsmobile.picquestv2.data.LocationRepository
import dev.mathewsmobile.picquestv2.data.UiStatus
import dev.mathewsmobile.picquestv2.data.UiStatus.Loaded
import dev.mathewsmobile.picquestv2.data.UiStatus.Loading
import dev.mathewsmobile.picquestv2.model.Location
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class LocationListUiState(
    val status: UiStatus = Loading,
    val locations: List<Location> = emptyList()
)

@HiltViewModel
class LocationListViewModel @Inject constructor(
    private val locationRepository: LocationRepository
) : ViewModel() {

    val state: StateFlow<LocationListUiState> = flow {
        emit(LocationListUiState(status = Loading))
        locationRepository.getLocations().collect { locations ->
            emit(LocationListUiState(Loaded, locations))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LocationListUiState())

}