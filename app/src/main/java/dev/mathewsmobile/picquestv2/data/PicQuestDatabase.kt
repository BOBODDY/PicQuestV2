package dev.mathewsmobile.picquestv2.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import dev.mathewsmobile.picquestv2.data.dao.LocationDao
import dev.mathewsmobile.picquestv2.data.dao.TagDao
import dev.mathewsmobile.picquestv2.model.db.LocationDb
import dev.mathewsmobile.picquestv2.model.db.LocationPhoto
import dev.mathewsmobile.picquestv2.model.db.LocationTagPair
import dev.mathewsmobile.picquestv2.model.db.TagDb

@Database(
    entities = [LocationDb::class, TagDb::class, LocationTagPair::class, LocationPhoto::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ]
)
abstract class PicQuestDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun tagDao(): TagDao
}