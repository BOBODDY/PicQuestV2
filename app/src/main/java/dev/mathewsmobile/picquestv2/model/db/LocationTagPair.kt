package dev.mathewsmobile.picquestv2.model.db

import androidx.room.Entity

@Entity(primaryKeys = ["locationId", "tagId"], tableName = "location_tag")
data class LocationTagPair(
    val locationId: Int,
    val tagId: Int
)
