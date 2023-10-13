package com.maurotellodev.tareascampo

import android.app.Application
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.HiltAndroidApp
import androidx.datastore.core.DataStore
import dagger.Provides


@HiltAndroidApp

class TareasCampoApp : Application() {
    val dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")
}