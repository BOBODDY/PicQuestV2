package dev.mathewsmobile.picquestv2.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class LocationDb(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    val name: String,
    val notes: String,
    val latitude: Float?,
    val longitude: Float?,
)
