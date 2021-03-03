package com.example.citykotlinproject

import android.app.Application
import androidx.room.Room
import com.example.citykotlinproject.local.AppDatabase

class ApplicationCity() : Application() {
    companion object{
        private lateinit var db: AppDatabase
        fun getDatabase(): AppDatabase = db
    }

    override fun onCreate() {
        super.onCreate()
        db = Room
            .databaseBuilder(applicationContext, AppDatabase::class.java, "city_db")
            .allowMainThreadQueries()
            .build()
    }
}