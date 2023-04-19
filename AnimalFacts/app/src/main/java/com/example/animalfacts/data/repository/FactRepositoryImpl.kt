package com.example.animalfacts.data.repository

import com.example.animalfacts.data.dao.FactDao
import com.example.animalfacts.data.entities.FactEntity
import kotlinx.coroutines.flow.Flow

class FactRepositoryImpl(private val dao: FactDao): FactRepository {
    override suspend fun insertFact(fact: FactEntity) {
        TODO("Not yet implemented")
    }

    override fun getAllFacts(): Flow<List<FactEntity>> {
        TODO("Not yet implemented")
    }

    override fun getFactById(id: String): Flow<FactEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFact(id: String) {
        TODO("Not yet implemented")
    }

}