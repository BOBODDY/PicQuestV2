package dev.mathewsmobile.picquestv2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.mathewsmobile.picquestv2.model.db.LocationTagPair
import dev.mathewsmobile.picquestv2.model.db.TagDb
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {
    @Query("SELECT * FROM tags")
    fun getAll(): Flow<List<TagDb>>

    @Query("SELECT * FROM tags WHERE id LIKE :tagId")
    suspend fun getTagById(tagId: Int): TagDb

    @Query("SELECT * FROM location_tag WHERE locationId LIKE :locationId")
    suspend fun getTagsForLocation(locationId: Int): List<LocationTagPair>

    @Insert
    suspend fun addLocationTagPair(pair: LocationTagPair)

    @Insert
    suspend fun addTag(tag: TagDb): Long
}