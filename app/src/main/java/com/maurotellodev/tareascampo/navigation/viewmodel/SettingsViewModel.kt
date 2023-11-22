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
import com.maurotellodev.tareascampo.utils.SERVER_PASSWORD
import com.maurotellodev.tareascampo.utils.SERVER_PORT
import com.maurotellodev.tareascampo.utils.SERVER_URL
import com.maurotellodev.tareascampo.utils.SERVER_USERNAME
import com.maurotellodev.tareascampo.utils.USERNAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: DataStoreRepository)  :ViewModel() {

    private val _username = MutableLiveData<String>()
    val username : LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    /******** START SERVER *********/

    private val _serverUrl = MutableLiveData<String>()
    val serverUrl : LiveData<String> = _serverUrl

    private val _serverPort = MutableLiveData<String>()
    val serverPort : LiveData<String> = _serverPort

    private val _serverUsername = MutableLiveData<String>()
    val serverUsername : LiveData<String> = _serverUsername

    private val _serverPassword = MutableLiveData<String>()
    val serverPassword : LiveData<String> = _serverPassword


    /******** END SERVER *********/

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable:LiveData<Boolean> = _isLoginEnable

    private val _isServerEnable = MutableLiveData<Boolean>()
    val isServerEnable:LiveData<Boolean> = _isServerEnable

    private val _preferencesSaved = MutableLiveData<Boolean>()
    var preferencesSaved:LiveData<Boolean> = _preferencesSaved


    var isDataSaved by mutableStateOf(false)


    fun onLoginChanged(username:String, password:String){
        _username.value = username
        _password.value = password
        _isLoginEnable.value = enableLogin(username, password)
    }

    fun onServerChanged(serverUrl:String, serverPort:String, serverUsername:String, serverPassword:String){
        _serverUrl.value = serverUrl
        _serverPort.value = serverPort
        _serverUsername.value = serverUsername
        _serverPassword.value = serverPassword
        _isServerEnable.value = enableServer(serverUrl, serverPort, serverUsername, serverPassword)
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
        }
    }

    fun onServerSelected(){
        viewModelScope.launch {
            val serverUrl = getServerUrlValue()
            val serverPort = getServerPortValue()
            val serverUsername = getServerUsernameValue()
            val serverPassword = getServerPasswordValue()
            val saved = saveServerData(serverUrl, serverPort, serverUsername, serverPassword)
            if(saved){
                _preferencesSaved.value = true
                isDataSaved = true
            }
            Log.i("aris", "Server URL: $serverUrl")
            Log.i("aris", "Server PORT: $serverPort")
            Log.i("aris", "Server USERNAME: $serverUsername")
            Log.i("aris", "Server PASSWORD: $serverPassword")
            Log.i("aris", "SERVER GUARDADOS EN DATASTORE")

            val savedserverUrl= getServerUrl()
            val savedserverPort= getServerPort()
            val savedserverUsername= getServerUsername()
            val savedserverPassword= getServerPassword()

            Log.i("aris", "savedserverUrl Saved: $savedserverUrl")
            Log.i("aris", "savedserverPort Saved: $savedserverPort")
            Log.i("aris", "savedserverUsername Saved: $savedserverUsername")
            Log.i("aris", "savedserverPassword Saved: $savedserverPassword")


            Log.i("aris", "PreferencesServerSaved: ${preferencesSaved.value}")
        }
    }

    private suspend fun saveServerData(serverUrl: String = "89.117.7.119", serverPort: String = "21", serverUsername: String = "u828369731.maurotellodev.com", serverPassword: String = "desaTELLO2014"):Boolean {
        return try {
            //Datos para probar

            repository.putString(SERVER_URL,serverUrl)
            repository.putString(SERVER_PORT, serverPort)
            repository.putString(SERVER_USERNAME, serverUsername)
            repository.putString(SERVER_PASSWORD, serverPassword)
            true
        }catch (e: Exception){
            false
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

    private fun enableServer(serverUrl: String, serverPort: String, serverUsername:String, serverPassword:String) =
        serverUrl.length > 2 && serverPort.length > 1 && serverUsername.length > 3 && serverPassword.length > 3


    private fun getUsername(): String? = runBlocking {
        repository.getString(USERNAME)
    }
    private fun getUsernameValue(): String{
        return _username.value ?: ""
    }

    private fun getPassword(): String? = runBlocking {
        repository.getString(PASSWORD)
    }

    private fun getPasswordValue(): String {
        return _password.value ?: ""
    }

    /**** SERVER *****/

    private fun getServerUrl(): String? = runBlocking {
        repository.getString(SERVER_URL)
    }

    private fun getServerUrlValue(): String {
        return _serverUrl.value ?: ""
    }

    private fun getServerPort(): String? = runBlocking {
        repository.getString(SERVER_PORT)
    }

    private fun getServerPortValue(): String {
        return _serverPort.value ?: ""
    }

    private fun getServerUsername(): String? = runBlocking {
        repository.getString(SERVER_USERNAME)
    }

    private fun getServerUsernameValue(): String {
        return _serverUsername.value ?: ""
    }

    private fun getServerPassword(): String? = runBlocking {
        repository.getString(SERVER_PASSWORD)
    }

    private fun getServerPasswordValue(): String {
        return _serverPassword.value ?: ""
    }
}