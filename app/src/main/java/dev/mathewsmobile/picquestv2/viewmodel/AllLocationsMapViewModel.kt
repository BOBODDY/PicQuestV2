package dev.mathewsmobile.picquestv2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mathewsmobile.picquestv2.data.UiStatus
import dev.mathewsmobile.picquestv2.data.usecase.FetchLocationsUseCase
import dev.mathewsmobile.picquestv2.model.Location
import dev.mathewsmobile.picquestv2.ui.screen.AllLocationsMapScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AllLocationsMapScreenState(
    val status: UiStatus = UiStatus.Loading,
    val locations: List<Location> = emptyList()
)

@HiltViewModel
class AllLocationsMapViewModel @Inject constructor(
    private val fetchLocationsUseCase: FetchLocationsUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(AllLocationsMapScreenState())
    val state: StateFlow<AllLocationsMapScreenState> = _state

    init {

        viewModelScope.launch {

            fetchLocationsUseCase().collect {
                _state.value = _state.value.copy(
                    status = UiStatus.Loaded,
                    locations = it
                )
            }
        }
    }
}