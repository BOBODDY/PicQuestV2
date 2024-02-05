package dev.mathewsmobile.picquestv2.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_photo")
data class LocationPhoto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val locationId: Int,
    val photoUri: String
)
