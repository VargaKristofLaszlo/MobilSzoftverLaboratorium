package com.example.animalfacts.data.repository

import com.example.animalfacts.data.entities.FactEntity
import kotlinx.coroutines.flow.Flow

interface FactRepository {
    suspend fun insertFact(fact: FactEntity)

    fun getAllFacts(): Flow<List<FactEntity>>

    fun getFactById(id: String): Flow<FactEntity>

    suspend fun deleteFact(id: String)
}