package com.maurotellodev.tareascampo.data.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.maurotellodev.tareascampo.data.Jobs
import kotlinx.coroutines.flow.Flow

@Dao
interface JobsDao {
    @Insert
    suspend fun insertJobs(job: Jobs)

    @Update
    suspend fun updateJobs(job: Jobs)

    @Delete
    suspend fun deleteJobs(job: Jobs)

    @Query("SELECT * FROM jobs")
    fun getAllJobs(): Flow<List<Jobs>>
}