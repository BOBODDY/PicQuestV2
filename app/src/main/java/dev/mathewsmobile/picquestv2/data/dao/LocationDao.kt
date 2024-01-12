package dev.mathewsmobile.picquestv2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.mathewsmobile.picquestv2.model.db.LocationDb
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Query("SELECT * from location")
    fun getAll(): Flow<List<LocationDb>>

    @Insert
    suspend fun addLocation(locationDb: LocationDb): Long
}