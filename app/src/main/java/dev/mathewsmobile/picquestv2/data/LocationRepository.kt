package dev.mathewsmobile.picquestv2.data

import android.net.Uri
import dev.mathewsmobile.picquestv2.data.dao.LocationDao
import dev.mathewsmobile.picquestv2.model.LatLng
import dev.mathewsmobile.picquestv2.model.Location
import dev.mathewsmobile.picquestv2.model.db.LocationPhoto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import dev.mathewsmobile.picquestv2.model.db.LocationDb as DbLocation

@Singleton
class LocationRepository @Inject constructor(
    private val locationDao: LocationDao,
    private val tagRepository: TagRepository,
) {

    companion object {
        val testLocations = mutableListOf<Location>(
            Location(
                name = "Pike's Peak",
                notes = "Pike's Peak is a very tall mountain in Colorado. It's very pretty.",
                tags = emptyList(),
                photoUris = emptyList(),
            ),
            Location(
                name = "Kennesaw Mountain",
                notes = "My first mountain. It would be good to get a shot at sunrise of the city.",
                tags = emptyList(),
                photoUris = emptyList(),
            ),
            Location(
                name = "Big Bend National Park",
                notes = "Perfect for astrophotography",
                tags = emptyList(),
                photoUris = emptyList(),
            ),
            Location(
                name = "Glacier",
                notes = "The glaciers in Iceland are very stark and beautiful.",
                tags = emptyList(),
                photoUris = emptyList(),
            ),
        )
    }

    fun getLocations(): Flow<List<Location>> {
        return locationDao.getAll().map { dbLocations ->
            dbLocations.map {
                val locationTags = tagRepository.getTagsForLocation(it.uid)
                val locationPhotos = locationDao.getLocationPhotos(it.uid)
                Location(
                    id = it.uid,
                    name = it.name,
                    notes = it.notes,
                    latLng = LatLng(latitude = it.latitude, longitude = it.longitude),
                    tags = locationTags,
                    photoUris = locationPhotos.map { Uri.parse(it.photoUri) }
                )
            }
        }
    }

    suspend fun getLocation(id: Int): Location? {
        val locationDb = locationDao.getLocation(id) ?: return null

        val locationTags = tagRepository.getTagsForLocation(id)
        val locationPhotos = locationDao.getLocationPhotos(id)

        return Location(
            id = id,
            name = locationDb.name,
            notes = locationDb.notes,
            latLng = LatLng(latitude = locationDb.latitude, longitude = locationDb.longitude),
            tags = locationTags,
            photoUris = locationPhotos.map { Uri.parse(it.photoUri) }
        )
    }

    suspend fun addLocation(location: Location) {
        val dbLocation = DbLocation(
            uid = 0,
            name = location.name,
            notes = location.notes,
            latitude = location.latLng?.latitude,
            longitude = location.latLng?.longitude,
        )
        val locationId = locationDao.addLocation(dbLocation).toInt()

        location.tags.forEach {
            tagRepository.addTagForLocation(locationId, it)
        }

        location.photoUris.forEach {
            locationDao.addLocationPhoto(LocationPhoto(
                locationId = locationId,
                photoUri = it.toString()
            ))
        }
    }
}