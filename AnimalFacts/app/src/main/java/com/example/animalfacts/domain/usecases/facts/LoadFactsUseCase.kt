package com.example.animalfacts.domain.usecases.facts

import com.example.animalfacts.domain.model.Fact
import com.example.animalfacts.domain.model.FactFilter
import com.example.animalfacts.network.FactService

class LoadFactsUseCase(private val service: FactService) {
    suspend operator fun invoke(filter: FactFilter): Result<List<Fact>> {
        return try {
            Result.success(service.getFacts(filter.animalType, filter.amount))
        } catch (e: Exception) {
            // TODO: Exception handling and a more precise type
            Result.failure(e)
        }
    }
}