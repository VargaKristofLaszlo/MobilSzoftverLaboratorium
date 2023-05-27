package com.example.animalfacts.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animalfacts.data.dao.FactDao
import com.example.animalfacts.data.entities.FactEntity

@Database(entities = [FactEntity::class], version = 3, exportSchema = false)
abstract class AnimalFactDatabase: RoomDatabase() {
    abstract  val dao: FactDao
}