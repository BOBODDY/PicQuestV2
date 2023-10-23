package dev.mathewsmobile.picquestv2.inject

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.mathewsmobile.picquestv2.data.PicQuestDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): PicQuestDatabase {
        return Room.databaseBuilder(
            appContext,
            PicQuestDatabase::class.java,
            "picquest-database"
        ).build()
    }
}