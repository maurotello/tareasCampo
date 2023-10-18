package com.maurotellodev.tareascampo.navigation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maurotellodev.tareascampo.data.DataStoreRepository
import com.maurotellodev.tareascampo.navigation.ui.screens.model.UserCredentials
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
            Log.i("aris", "DATOS GUARDADOS EN DATASTORE")
            val savedUsername = getUsername();
            Log.i("aris", "Username: $savedUsername")
            val savedPassword = getPassword();
            Log.i("aris", "Username: $savedPassword")

            //}
        }
    }


    suspend fun saveUserData(username: String, password: String) {
        val userCredentials = UserCredentials(username, password)
        repository.putString(USERNAME,username)
        repository.putString(PASSWORD,password)
    }

    fun enableLogin(username: String, password: String) =
        username.length > 2 && password.length > 2


    fun getUsernameValue(): String{
        return _username.value ?: ""
    }

    fun getPasswordValue(): String {
        return _password.value ?: ""
    }

    fun getUsername(): String? = runBlocking {
        repository.getString(USERNAME)
    }

    fun getPassword(): String? = runBlocking {
        repository.getString(PASSWORD)
    }
}