package dev.mathewsmobile.picquestv2.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mathewsmobile.picquestv2.data.LocationRepository
import dev.mathewsmobile.picquestv2.data.TagRepository
import dev.mathewsmobile.picquestv2.model.Location
import dev.mathewsmobile.picquestv2.model.Tag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewLocationViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val tagRepository: TagRepository,
) : ViewModel() {

    val tags = tagRepository.getAllTags()

    private val _selectedTags = MutableStateFlow(emptyList<Tag>())
    val selectedTags = _selectedTags.asStateFlow()

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

    fun addNewLocation(name: String, notes: String, tags: List<Tag>) {
        viewModelScope.launch {
            locationRepository.addLocation(Location(name = name, notes = notes, tags = tags))
        }
    }

    fun toggleTag(tag: Tag) {
        val tags = _selectedTags.value
        val newTags = if (tags.contains(tag)) {
            tags - tag
        } else {
            tags + tag
        }
        viewModelScope.launch {
            _selectedTags.emit(newTags)
        }
    }
}