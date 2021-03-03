package com.example.citykotlinproject.local

import androidx.room.*
import com.example.citykotlinproject.models.City

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCity(data: List<City>?)

    @Query("SELECT * FROM city")
    fun fetchFavoriteCity(): MutableList<City>
}