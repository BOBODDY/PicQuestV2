package dev.mathewsmobile.picquestv2.viewmodel

import android.graphics.Point
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mathewsmobile.picquestv2.data.LocationRepository
import dev.mathewsmobile.picquestv2.data.TagRepository
import dev.mathewsmobile.picquestv2.model.LatLng
import dev.mathewsmobile.picquestv2.model.Location
import dev.mathewsmobile.picquestv2.model.Tag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewLocationViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    tagRepository: TagRepository,
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

    private var locationPoint: com.google.android.gms.maps.model.LatLng? = null

    private val _photosState = MutableStateFlow(emptyList<Uri>())
    val photos = _photosState.asStateFlow()

    fun clear() {
        viewModelScope.launch {
            _selectedTags.emit(emptyList())
            _nameState.emit("")
            _notesState.emit("")
            _photosState.emit(emptyList())
        }
    }

    fun addNewLocation(name: String, notes: String, tags: List<Tag>) {
        viewModelScope.launch {
            val location = Location(
                name = name,
                notes = notes,
                tags = tags,
                latLng = LatLng(
                    locationPoint?.latitude?.toFloat(),
                    locationPoint?.longitude?.toFloat()
                ),
                photoUris = _photosState.value
            )
            locationRepository.addLocation(location)
        }
    }

    fun addNewLocation() {
        viewModelScope.launch {
            val location = Location(
                name = name.value,
                notes = notes.value,
                tags = selectedTags.value,
                latLng = LatLng(
                    locationPoint?.latitude?.toFloat(),
                    locationPoint?.longitude?.toFloat()
                ),
                photoUris = _photosState.value
            )
            locationRepository.addLocation(location)
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

    fun setLocation(point: com.google.android.gms.maps.model.LatLng) {
        locationPoint = point
    }

    fun addPhoto(uri: Uri) {
        viewModelScope.launch {
            _photosState.emit(_photosState.value + uri)
        }
    }
}