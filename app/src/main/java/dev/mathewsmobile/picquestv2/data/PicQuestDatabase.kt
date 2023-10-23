package dev.mathewsmobile.picquestv2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.mathewsmobile.picquestv2.data.dao.LocationDao
import dev.mathewsmobile.picquestv2.model.db.Location

@Database(entities = [Location::class], version = 1, exportSchema = false)
abstract class PicQuestDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}