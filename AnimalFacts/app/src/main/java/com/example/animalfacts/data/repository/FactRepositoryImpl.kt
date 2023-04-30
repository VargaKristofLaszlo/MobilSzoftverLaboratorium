package com.example.animalfacts.data.repository

import com.example.animalfacts.data.dao.FactDao
import com.example.animalfacts.data.entities.FactEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FactRepositoryImpl @Inject constructor(private val dao: FactDao): FactRepository {
    override suspend fun insertFact(fact: FactEntity) {
       dao.insertFact(fact)
    }

    override fun getAllFacts(): Flow<List<FactEntity>> {
        return dao.getAllFacts()
    }

    override fun getFactById(id: String): Flow<FactEntity> {
        return dao.getFactById(id)
    }

    override suspend fun deleteFact(id: String) {
        dao.deleteFact(id)
    }

}