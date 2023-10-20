package dev.mathewsmobile.picquestv2.data

import dev.mathewsmobile.picquestv2.model.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepository @Inject constructor() {

    private var _locations = mutableListOf<Location>(
        Location(
            name = "Pike's Peak",
            notes = "Pike's Peak is a very tall mountain in Colorado. It's very pretty."
        ),
        Location(
            name = "Kennesaw Mountain",
            notes = "My first mountain. It would be good to get a shot at sunrise of the city."
        ),
        Location(
            name = "Big Bend National Park",
            notes = "Perfect for astrophotography"
        ),
        Location(
            name = "Glacier",
            notes = "The glacier's in Iceland are very stark and beautiful."
        ),
    )
    private val locations: MutableStateFlow<List<Location>> = MutableStateFlow(_locations)

    fun getLocations(): Flow<List<Location>> {
        return locations.asStateFlow()
    }

    suspend fun addLocation(location: Location) {
        _locations.add(location)
        locations.emit(_locations)
    }
}