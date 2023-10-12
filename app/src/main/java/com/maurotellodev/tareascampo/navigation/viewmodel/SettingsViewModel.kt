package com.maurotellodev.tareascampo.navigation.viewmodel

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")
@HiltViewModel
class SettingsViewModel @Inject constructor()  :ViewModel() {
    //private val preferencesManager: PreferencesManager
    //private val dataStore: DataStore<Preferences> =

    suspend fun saveUserData(username: String, password: String){
        //preferencesManager.saveUserData(username, password)
    }

    suspend fun getUsername(): String{
        //return preferencesManager.getUsername()
        return "Username"
    }
    suspend fun getPassword(): String{
        //return preferencesManager.getPassword()
        return "Password"
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