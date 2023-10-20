package dev.mathewsmobile.picquestv2.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mathewsmobile.picquestv2.data.LocationRepository
import javax.inject.Inject

@HiltViewModel
class NewLocationViewModel @Inject constructor(
    private val locationRepository: LocationRepository
) : ViewModel(){
}