package dev.mathewsmobile.picquestv2.viewmodel

import android.net.Uri
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mapbox.geojson.Point
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

    private var locationPoint: Point? = null

    private val _photosState = MutableStateFlow(emptyList<Uri>())
    val photos = _photosState.asStateFlow()

    fun addNewLocation(name: String, notes: String, tags: List<Tag>) {
        viewModelScope.launch {
            val location = Location(
                name = name,
                notes = notes,
                tags = tags,
                latLng = LatLng(
                    locationPoint?.latitude()?.toFloat(),
                    locationPoint?.longitude()?.toFloat()
                )
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

    fun setLocation(point: Point) {
        locationPoint = point
    }

    fun addPhoto(uri: Uri) {
        viewModelScope.launch {
            _photosState.emit(_photosState.value + uri)
        }
    }
}