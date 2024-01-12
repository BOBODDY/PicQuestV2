package dev.mathewsmobile.picquestv2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.mathewsmobile.picquestv2.data.dao.LocationDao
import dev.mathewsmobile.picquestv2.data.dao.TagDao
import dev.mathewsmobile.picquestv2.model.db.LocationDb
import dev.mathewsmobile.picquestv2.model.db.LocationTagPair
import dev.mathewsmobile.picquestv2.model.db.TagDb

@Database(entities = [LocationDb::class, TagDb::class, LocationTagPair::class], version = 1, exportSchema = false)
abstract class PicQuestDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun tagDao(): TagDao
}