package com.maurotellodev.tareascampo

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
@HiltAndroidApp

class TareasCampoApp : Application() {
    val dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

}