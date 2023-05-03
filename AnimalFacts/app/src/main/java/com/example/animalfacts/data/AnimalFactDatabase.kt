package com.example.animalfacts.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.animalfacts.data.converters.DateConverter
import com.example.animalfacts.data.dao.FactDao
import com.example.animalfacts.data.entities.FactEntity

@Database(entities = [FactEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AnimalFactDatabase: RoomDatabase() {
    abstract  val dao: FactDao
}