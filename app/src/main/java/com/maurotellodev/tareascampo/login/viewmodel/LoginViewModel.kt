package com.maurotellodev.tareascampo.login.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.maurotellodev.tareascampo.data.DataStoreRepository
import com.maurotellodev.tareascampo.navigation.Destinations
import com.maurotellodev.tareascampo.utils.PASSWORD
import com.maurotellodev.tareascampo.utils.USERNAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: DataStoreRepository, private val context: Context)  :ViewModel() {

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _emailvalidate = MutableLiveData<Boolean>()
    val emailvalidate : LiveData<Boolean> = _emailvalidate

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _passwordvalidate = MutableLiveData<Boolean>()
    val passwordvalidate : LiveData<Boolean> = _passwordvalidate

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable:LiveData<Boolean> = _isLoginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    var isLoading:LiveData<Boolean> = _isLoading

    fun onLoginChanged(email:String, password:String) {
        _email.value = email
        _password.value = password
        _emailvalidate.value = emailvalue(email)
        _passwordvalidate.value = passwordvalue(password)
        _isLoginEnable.value = enableLogin(email, password)
    }

    private fun emailvalue(email: String):Boolean{
        return (email == "admin" || getUsername() == email)
    }

    private fun passwordvalue(password: String):Boolean{
        return (password == "123" || getPassword() == password)
    }

    fun enableLogin(email: String, password: String):Boolean{
        return true
    }

    fun onLoginSelected(navController: NavController, email:String, password:String){
        viewModelScope.launch {
            _isLoading.value = true
            Log.i("aris", "result OK")
            if (validateCredentials(email, password)) {
                if(createGesisFolder()){
                    Log.i("aris", "Carpeta CREADA")
                    navController.navigate(Destinations.BottomNavigationBar.route)
                }else{
                    Log.i("aris", "ERROR AL CREAR LA CARPETA GESIS")
                    navController.navigate(Destinations.BottomNavigationBar.route)
                }
            } else {
                _emailvalidate.value = email != "admin"
                _passwordvalidate.value = email != "123"
            }
        }
    }

    fun createGesisFolder():Boolean {

        // Obtén la ubicación del directorio de archivos externos de la aplicación.
        val externalFilesDir = context.getExternalFilesDir(null)

        // Comprueba si el directorio "gesis" ya existe en el directorio de archivos externos.
        val gesisFolder = File(externalFilesDir, "gesis")

        // Comprueba si el directorio existe. Si no, créalo.
        if (!gesisFolder.exists()) {
            // Si no existe, crea la carpeta "gesis".
            return gesisFolder.mkdirs()
        } else {
            return true
            // La carpeta "gesis" ya existe, puedes manejarlo como desees.
        }
    }

    fun validateCredentials(email: String, password: String): Boolean {
        Log.i("aris", "email = $email")
        Log.i("aris", "password = $password")
        return true
    }

    private fun getUsername(): String? = runBlocking {
        repository.getString(USERNAME)
    }

    private fun getPassword(): String? = runBlocking {
        repository.getString(PASSWORD)
    }
}