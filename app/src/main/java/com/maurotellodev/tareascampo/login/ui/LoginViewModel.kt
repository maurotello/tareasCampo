package com.maurotellodev.tareascampo.login.ui

import android.annotation.SuppressLint
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.maurotellodev.tareascampo.navigation.ui.BottomNavigationBar
import kotlinx.coroutines.launch


// Con esta anotación preparamos el viewModel para ser inyectado
class LoginViewModel:ViewModel() {

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable:LiveData<Boolean> = _isLoginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    var isLoading:LiveData<Boolean> = _isLoading

    fun onLoginChanged(email:String, password:String){
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email, password)
    }

    fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6


    fun onLoginSelected(){
        viewModelScope.launch {
            _isLoading.value = true
            //val result = loginUseCase(email.value!!, password.value!!)
           // if (result){
            Log.i("aris", "result OK")
            //}
            _isLoading.value = false
        }
    }


}