package com.maurotellodev.tareascampo.login.data

import com.maurotellodev.tareascampo.login.data.network.LoginService

class LoginRepository {
    private val api = LoginService()

    // Esta va a ser la llamada que llame al dominio
    suspend fun doLogin(user:String, password:String):Boolean{
        return api.doLogin(user, password)
    }
}