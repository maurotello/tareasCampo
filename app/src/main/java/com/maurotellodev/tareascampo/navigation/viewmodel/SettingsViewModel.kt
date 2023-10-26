package com.maurotellodev.tareascampo.navigation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maurotellodev.tareascampo.data.interfaces.DataStoreRepository
import com.maurotellodev.tareascampo.utils.PASSWORD
import com.maurotellodev.tareascampo.utils.USERNAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: DataStoreRepository)  :ViewModel() {


    //private val preferencesKey = stringPreferencesKey("example_preference")

    private val _username = MutableLiveData<String>()
    val username : LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable:LiveData<Boolean> = _isLoginEnable

    private val _preferencesSaved = MutableLiveData<Boolean>()
    var preferencesSaved:LiveData<Boolean> = _preferencesSaved

    var isDataSaved by mutableStateOf(false)


    fun onLoginChanged(username:String, password:String){
        _username.value = username
        _password.value = password
        _isLoginEnable.value = enableLogin(username, password)
    }


    fun onLoginSelected(){

        viewModelScope.launch {
            val username = getUsernameValue()
            val password = getPasswordValue()
            val saved = saveUserData(username, password)
            if(saved){
                _preferencesSaved.value = true
                isDataSaved = true
            }

            Log.i("aris", "USERNAME: $username")
            Log.i("aris", "PASSWORD: $password")
            Log.i("aris", "DATOS GUARDADOS EN DATASTORE")
            val savedUsername = getUsername()
            val savedPassword = getPassword()
            Log.i("aris", "Password Saved: $savedPassword")
            Log.i("aris", "Username Saved: $savedUsername")
            Log.i("aris", "PreferencesSaved: ${preferencesSaved.value}")

            //}
        }
    }


    private suspend fun saveUserData(username: String, password: String):Boolean {
        return try {
            repository.putString(USERNAME,username)
            repository.putString(PASSWORD,password)
            true
        }catch (e: Exception){
            false
        }
    }

    private fun enableLogin(username: String, password: String) =
        username.length > 2 && password.length > 2


    private fun getUsernameValue(): String{
        return _username.value ?: ""
    }

    private fun getPasswordValue(): String {
        return _password.value ?: ""
    }

    private fun getUsername(): String? = runBlocking {
        repository.getString(USERNAME)
    }

    private fun getPassword(): String? = runBlocking {
        repository.getString(PASSWORD)
    }
}