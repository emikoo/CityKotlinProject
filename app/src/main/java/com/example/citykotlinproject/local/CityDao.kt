package com.example.citykotlinproject.local

import androidx.room.*
import com.example.citykotlinproject.models.City

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(data: City?)

    @Delete
    fun deleteCity(data: City?)

    @Query("SELECT * FROM city")
    fun fetchFavoriteCity(): MutableList<City>
}