package com.maurotellodev.tareascampo.di

import com.maurotellodev.tareascampo.data.repository.JobRepository
import com.maurotellodev.tareascampo.data.repository.JobRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun jobsRepository(repo: JobRepositoryImp) : JobRepository
}