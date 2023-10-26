package com.maurotellodev.tareascampo.data.repository

import com.maurotellodev.tareascampo.data.interfaces.RestDataSource
import com.maurotellodev.tareascampo.model.ApiResponse
import javax.inject.Inject

interface JobRepository {
    suspend fun getJobs(): ApiResponse
}

class JobRepositoryImp @Inject constructor(private val dataSource: RestDataSource) :
    JobRepository {
    override suspend fun getJobs(): ApiResponse {
        return dataSource.getJobs2()
    }
}