package dev.mathewsmobile.picquestv2.data

import dev.mathewsmobile.picquestv2.model.Location
import javax.inject.Inject

class LocationRepository @Inject constructor() {

    private var locations = mutableListOf<Location>(
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

    fun getLocations(): List<Location> {
        return locations
    }

    fun addLocation(location: Location) {
        locations.add(location)
    }
}