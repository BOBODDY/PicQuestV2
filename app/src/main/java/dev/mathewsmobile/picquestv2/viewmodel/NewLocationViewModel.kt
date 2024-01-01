package dev.mathewsmobile.picquestv2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mathewsmobile.picquestv2.data.LocationRepository
import dev.mathewsmobile.picquestv2.model.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewLocationViewModel @Inject constructor(
    private val locationRepository: LocationRepository
) : ViewModel(){

    private val _nameState = MutableStateFlow("")
    val name = _nameState.asStateFlow()
    fun setName(name: String) {
        viewModelScope.launch {
            _nameState.emit(name)
        }
    }

    private val _notesState = MutableStateFlow("")
    val notes = _notesState.asStateFlow()
    fun setNotes(notes: String) {
        viewModelScope.launch {
            _notesState.emit(notes)
        }

    }

    fun addNewLocation(name: String, notes: String) {
        viewModelScope.launch {
            locationRepository.addLocation(Location(name = name, notes = notes, tags = emptyList()))
        }
    }
}