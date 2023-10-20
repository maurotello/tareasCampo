package com.maurotellodev.tareascampo.navigation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.maurotellodev.tareascampo.data.Jobs
import com.maurotellodev.tareascampo.data.interfaces.JobsDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OptionsViewModel @Inject constructor(private val jobsDao: JobsDao) : ViewModel(){

    val jobs: LiveData<List<Jobs>> = jobsDao.getAllJobs().asLiveData()

    fun insertJob(job: Jobs){
        viewModelScope.launch {
            jobsDao.insertJobs(job)
        }
    }

    fun updateJob(job: Jobs){
        viewModelScope.launch {
            jobsDao.updateJobs(job)
        }
    }

    fun deleteJob(job: Jobs){
        viewModelScope.launch {
            jobsDao.deleteJobs(job)
        }
    }
}