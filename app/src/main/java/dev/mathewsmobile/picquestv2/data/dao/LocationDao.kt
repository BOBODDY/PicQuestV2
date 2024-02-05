package dev.mathewsmobile.picquestv2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.mathewsmobile.picquestv2.model.Location
import dev.mathewsmobile.picquestv2.model.db.LocationDb
import dev.mathewsmobile.picquestv2.model.db.LocationPhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Query("SELECT * from location")
    fun getAll(): Flow<List<LocationDb>>

    @Query("SELECT * from location WHERE uid LIKE :locationId")
    suspend fun getLocation(locationId: Int): LocationDb?

    @Insert
    suspend fun addLocation(locationDb: LocationDb): Long

    @Query("SELECT * from location_photo WHERE locationId LIKE :locationId")
    suspend fun getLocationPhotos(locationId: Int): List<LocationPhoto>

    @Insert
    suspend fun addLocationPhoto(photo: LocationPhoto)
}