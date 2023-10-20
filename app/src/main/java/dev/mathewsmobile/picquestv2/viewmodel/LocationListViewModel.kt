package dev.mathewsmobile.picquestv2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mathewsmobile.picquestv2.data.LocationRepository
import dev.mathewsmobile.picquestv2.data.UiStatus
import dev.mathewsmobile.picquestv2.data.UiStatus.Initial
import dev.mathewsmobile.picquestv2.data.UiStatus.Loaded
import dev.mathewsmobile.picquestv2.data.UiStatus.Loading
import dev.mathewsmobile.picquestv2.model.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LocationListUiState(
    val status: UiStatus = Initial,
    val locations: List<Location> = emptyList()
)

@HiltViewModel
class LocationListViewModel @Inject constructor(
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LocationListUiState())
    val uiStateFlow: StateFlow<LocationListUiState> = _uiState.asStateFlow()

    fun fetchLocations() {
        viewModelScope.launch {
            setLoading(true)
            val locations = locationRepository.getLocations()
            updateLocations(locations)
            setLoading(false)
        }
    }

    private fun setLoading(isLoading: Boolean) {
        val status = if (isLoading) Loading else Loaded
        _uiState.update { currentState ->
            currentState.copy(status = status)
        }
    }
    private fun updateLocations(locations: List<Location>) {
        _uiState.update { currentState ->
            currentState.copy(locations = locations)
        }
    }
}