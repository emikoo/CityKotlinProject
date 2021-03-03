package com.example.citykotlinproject.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "city")

data class City(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    var name: String,
    var region: String? = null,
    var subRegion: String? =  null,
    var capital: String? = null,
    var area: String? =  null,
    var flag: String? = null
)