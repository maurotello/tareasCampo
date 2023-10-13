package com.maurotellodev.tareascampo.navigation.ui.screens.model

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class UserRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveUserCredentials(credentials: UserCredentials) {
        val usernameKey = stringPreferencesKey("username")
        val passwordKey = stringPreferencesKey("password")

        dataStore.edit { preferences ->
            preferences[usernameKey] = credentials.username
            preferences[passwordKey] = credentials.password
        }
    }

    suspend fun getUserCredentials(): UserCredentials {
        val usernameKey = stringPreferencesKey("username")
        val passwordKey = stringPreferencesKey("password")
        val data = dataStore.data.first()

        val username = data[usernameKey] ?: ""
        val password = data[passwordKey] ?: ""

        return UserCredentials(username, password)
    }
}
