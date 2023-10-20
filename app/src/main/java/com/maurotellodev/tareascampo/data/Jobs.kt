package com.maurotellodev.tareascampo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date

@Entity(tableName = "jobs")
data class Jobs(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val date: Long,
    val quantity: Int
){
    // Método para convertir de Date a Long
    @TypeConverter
    fun dateToLong(date: Date): Long {
        return date.time
    }

    // Método para convertir de Long a Date
    @TypeConverter
    fun longToDate(longValue: Long): Date {
        return Date(longValue)
    }
}
