package com.maurotellodev.tareascampo.navigation.ui

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.first

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class SettingsViewModel(private val preferencesManager: PreferencesManager): ViewModel() {
    //private val dataStore: DataStore<Preferences> = TODO()

    suspend fun saveUserData(username: String, password: String){
        preferencesManager.saveUserData(username, password)
    }

    suspend fun getUsername(): String{
        return preferencesManager.getUsername()
    }
    suspend fun getPassword(): String{
        return preferencesManager.getPassword()
    }

    /*
    suspend fun saveUserData(username: String, password: String) {
        val usernameKey = stringPreferencesKey("username")
        val passwordKey = stringPreferencesKey("password")

        dataStore.edit { preferences ->
            preferences[usernameKey] = username
            preferences[passwordKey] = password
        }
    }
    */

}