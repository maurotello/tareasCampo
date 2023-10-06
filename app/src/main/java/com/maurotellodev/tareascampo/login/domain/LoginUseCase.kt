package com.maurotellodev.tareascampo.login.domain

import com.maurotellodev.tareascampo.login.data.LoginRepository


// El ViewModel es el encargado de llamar a los casos de uso, cuando se ejecute una acción
// por ejemplo, presionamos un botón, por lo tanto
// deberíamos llamara esta función
class LoginUseCase {
    private val repository = LoginRepository()

    suspend operator fun invoke(user:String, password:String):Boolean{
        return repository.doLogin(user, password)
    }
}