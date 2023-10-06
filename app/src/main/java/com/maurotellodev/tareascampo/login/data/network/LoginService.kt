package com.maurotellodev.tareascampo.login.data.network

import com.maurotellodev.tareascampo.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Es la encargada de llamar a todos los endpoints que tuviera en LoginClient
// Y es la que va a tener el objeto Retrofit que cramos
class LoginService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun doLogin(user:String, password:String):Boolean{
        // Con el Dispacher cambio el hilo de ejecución porque esto es una coroutina que no se puede
        // ejecutar en el hilo MAIN
        return withContext(Dispatchers.IO){
            // El response es lo que nos devuelve el servidor, es decir el endpoint
            val response = retrofit.create(LoginClient::class.java).doLogin()
            // val response = retrofit.create(LoginClient::class.java).doLogin(user, password)
            // El verdadero login seria con user y password pero como no lo tenemos implementados y para que no se
            // rompa la APP lo sacamos

            // El atributo success es el mismo que tengo en el LoginResponse
            response.body()?.success ?: false
        }
    }
}