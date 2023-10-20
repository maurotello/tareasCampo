package com.maurotellodev.tareascampo.di

import android.content.Context
import androidx.room.Room
import com.maurotellodev.tareascampo.data.databases.AppDatabase
import com.maurotellodev.tareascampo.data.interfaces.JobsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideJobsDao(database: AppDatabase): JobsDao {
        return database.jobDao()
    }
}