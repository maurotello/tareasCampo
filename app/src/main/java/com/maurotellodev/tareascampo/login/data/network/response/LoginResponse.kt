package com.maurotellodev.tareascampo.login.data.network.response

import com.google.gson.annotations.SerializedName

// el success es lo que recibo del endpoint en este caso recibo solamente success
// pero en el json podrían haber otras cosas

data class LoginResponse(@SerializedName("success") val success:Boolean)