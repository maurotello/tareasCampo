package com.maurotellodev.tareascampo.login.data.network


import com.maurotellodev.tareascampo.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

// Dentro de doLogin si esto fuera algo bien hecho de la realidad
// iría lo siguiente porque recibiría esos datos del endpoint
// doLogin(user:String, password:String) y después lo mismo

// Si el dia de mañana quiero cambiar el apiCliente por firebase en vez de llamar a esto
// llamaría al FirebaseClient y  el resto funciona
interface LoginClient {
    @GET("/v3/f78c9d33-28b1-4f81-9cf1-6d6ff78fa014")
    suspend fun doLogin():Response<LoginResponse>
    //suspend fun doLogin(user:String, password:String):Response<LoginResponse>
    // El verdadero login seria con user y password pero como no lo tenemos implementados y para que no se
    // rompa la APP lo sacamos
}