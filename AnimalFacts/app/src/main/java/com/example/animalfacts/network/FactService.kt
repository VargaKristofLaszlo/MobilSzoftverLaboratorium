package com.example.animalfacts.network

import com.example.animalfacts.domain.model.Fact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FactServiceImpl @Inject constructor(): FactService {
    override fun getFacts(animal_type: String, amount: Int): Flow<List<Fact>> {
        TODO("Not yet implemented")
    }

    override fun getFactById(id: String): Flow<Fact> {
        TODO("Not yet implemented")
    }

}