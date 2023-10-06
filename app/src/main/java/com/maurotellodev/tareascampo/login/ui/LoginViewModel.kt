package com.maurotellodev.tareascampo.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maurotellodev.tareascampo.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// Con esta anotación preparamos el viewModel para ser inyectado
@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    // Llamo al caso de uso que está en domain
    val LoginUseCase = LoginUseCase()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email, password)
    }

    fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

    // Se ejecuta cuando el botón se presione
    fun onLoginSelected() {
        // Esto va a lanzar el viewModel con la Coroutina
        viewModelScope.launch {
            _isLoading.value = true;
            val result = LoginUseCase(email.value!!, password.value!!)
            if (result) {
                //Navegar a la siguiente pantalla
                Log.i("aris", "result OK")
            }
            _isLoading.value = false;
        }

    }
}