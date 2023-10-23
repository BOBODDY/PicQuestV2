package dev.mathewsmobile.picquestv2.inject

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mathewsmobile.picquestv2.data.PicQuestDatabase
import dev.mathewsmobile.picquestv2.data.dao.LocationDao

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun providesLocationDao(
        database: PicQuestDatabase
    ) : LocationDao = database.locationDao()
}