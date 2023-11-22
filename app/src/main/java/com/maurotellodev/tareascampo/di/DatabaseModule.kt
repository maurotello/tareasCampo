package com.maurotellodev.tareascampo.di

import android.content.Context
import com.maurotellodev.tareascampo.data.databases.DatabaseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideDatabaseHelper(context: Context): DatabaseHelper {
        return DatabaseHelper(context)
    }
}