package com.maurotellodev.tareascampo.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maurotellodev.tareascampo.data.Jobs
import com.maurotellodev.tareascampo.data.interfaces.JobsDao

@Database(entities = [Jobs::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jobDao(): JobsDao
}