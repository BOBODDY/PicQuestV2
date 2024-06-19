package dev.mathewsmobile.picquestv2.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mathewsmobile.picquestv2.data.LocationRepository
import dev.mathewsmobile.picquestv2.model.Location
import dev.mathewsmobile.picquestv2.model.Tag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewLocationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val locationRepository: LocationRepository,
): ViewModel() {

    val location = flow {
        val locationId = savedStateHandle.get<Int>("locationId")
        val location = locationId?.let { locationRepository.getLocation(it) }
        emit(location)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
}

class ViewLocationViewModelFactory(private val locationRepository: LocationRepository, private val locationId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val savedStateHandle = extras.createSavedStateHandle()
        savedStateHandle["locationId"] = locationId
        return ViewLocationViewModel(savedStateHandle, locationRepository) as T
    }
}