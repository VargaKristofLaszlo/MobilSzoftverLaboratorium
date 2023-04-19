package com.example.animalfacts.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animalfacts.data.entities.FactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFact(fact: FactEntity)

    @Query("SELECT * FROM fact_table")
    fun getAllFacts(): Flow<List<FactEntity>>

    @Query("SELECT * FROM fact_table WHERE _id = :id")
    fun getFactById(id: String): Flow<FactEntity>

    @Query("DELETE FROM fact_table WHERE _id = :id")
    suspend fun deleteFact(id: String)
}