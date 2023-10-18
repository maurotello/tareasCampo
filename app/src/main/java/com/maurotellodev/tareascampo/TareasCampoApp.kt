package com.maurotellodev.tareascampo

import android.app.Application
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent


@HiltAndroidApp

class TareasCampoApp : Application() {
    //val dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

}