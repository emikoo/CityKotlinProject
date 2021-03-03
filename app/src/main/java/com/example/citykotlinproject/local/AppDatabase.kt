package com.example.citykotlinproject.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.citykotlinproject.models.City

@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao
}