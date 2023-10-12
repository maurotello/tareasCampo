package com.maurotellodev.tareascampo.data
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")
class PreferencesManager(private val dataStore: DataStore<Preferences>) {

    suspend fun saveUserData(username: String, password: String) {
        val usernameKey = stringPreferencesKey("username")
        val passwordKey = stringPreferencesKey("password")

        dataStore.edit {  preferences ->
            preferences[usernameKey] = username
            preferences[passwordKey] = password
        }
    }

    suspend fun getUsername(): String {
        val usernameKey = stringPreferencesKey("username")
        val data = dataStore.data.first()
        return data[usernameKey] ?: ""
    }

    suspend fun getPassword(): String {
        val passwordKey = stringPreferencesKey("password")
        val data = dataStore.data.first()
        return data[passwordKey] ?: ""
    }
}