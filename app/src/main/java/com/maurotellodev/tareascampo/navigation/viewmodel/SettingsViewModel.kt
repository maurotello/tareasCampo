package com.maurotellodev.tareascampo.navigation.viewmodel

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maurotellodev.tareascampo.navigation.ui.screens.model.UserCredentials
import com.maurotellodev.tareascampo.navigation.ui.screens.model.UserRepository
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(private val userRepository: UserRepository)  :ViewModel() {


    private val _username = MutableLiveData<String>()
    val username : LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable:LiveData<Boolean> = _isLoginEnable


    fun onLoginChanged(username:String, password:String){
        _username.value = username
        _password.value = password
        _isLoginEnable.value = enableLogin(username, password)
    }


    fun onLoginSelected(){
        viewModelScope.launch {

            //val result = loginUseCase(email.value!!, password.value!!)
            // if (result){
            Log.i("aris", "USERNAME: ")
            val username = getUsernameValue()
            val password = getPasswordValue()
            saveUserData(username, password)
            Log.i("aris", "USERNAME: $username")
            Log.i("aris", "PASSWORD: $password")

            //}
        }
    }


    suspend fun saveUserData(username: String, password: String) {
        val userCredentials = UserCredentials(username, password)
        userRepository.saveUserCredentials(userCredentials)
    }

    fun enableLogin(username: String, password: String) =
        username.length > 2 && password.length > 2


    fun getUsernameValue(): String{
        return _username.value ?: ""
    }

    fun getPasswordValue(): String {
        return _password.value ?: ""
    }

    suspend fun getUsername(): String {
        return userRepository.getUserCredentials().username
    }

    suspend fun getPassword(): String {
        return userRepository.getUserCredentials().password
    }
}