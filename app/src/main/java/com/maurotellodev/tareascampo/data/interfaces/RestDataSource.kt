package com.maurotellodev.tareascampo.data.interfaces

import com.maurotellodev.tareascampo.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface RestDataSource {
    @Headers("Accept: application/json")
    @GET("api/process-json")
    suspend fun getJobs2():ApiResponse
}