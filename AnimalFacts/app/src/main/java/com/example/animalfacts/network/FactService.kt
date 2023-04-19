package com.example.animalfacts.network

import com.example.animalfacts.domain.model.Fact
import kotlinx.coroutines.flow.Flow

interface FactService {
    fun getFacts(animal_type: String, amount: Int): Flow<List<Fact>>

    fun getFactById(id: String): Flow<Fact>
}