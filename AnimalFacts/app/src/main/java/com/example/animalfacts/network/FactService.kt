package com.example.animalfacts.network

import com.example.animalfacts.domain.model.Fact
import retrofit2.Call

interface FactService {
    suspend fun getFacts(animal_type: String, amount: Int): List<Fact>

    suspend fun getFactById(id: String): Fact
}