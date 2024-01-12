package dev.mathewsmobile.picquestv2.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class TagDb(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String
)
