package com.example.animalfacts.network

import com.example.animalfacts.domain.model.Fact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FactService @Inject constructor() {
    fun getFacts(animal_type: String, amount: Int): Flow<List<Fact>> {
        TODO("Not yet implemented")
    }

    fun getFactById(id: String): Flow<Fact> {
        TODO("Not yet implemented")
    }
}