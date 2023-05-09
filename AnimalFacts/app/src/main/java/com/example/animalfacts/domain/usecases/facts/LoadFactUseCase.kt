package com.example.animalfacts.domain.usecases.facts

import com.example.animalfacts.domain.model.Fact
import com.example.animalfacts.network.FactService

class LoadFactUseCase(private val service: FactService) {
    suspend operator fun invoke(id: String): Result<Fact> {
        return try {
            Result.success(service.getFactById(id))
        } catch (e: Exception) {
            // TODO: Exception handling and a more precise type
            Result.failure(e)
        }
    }
}