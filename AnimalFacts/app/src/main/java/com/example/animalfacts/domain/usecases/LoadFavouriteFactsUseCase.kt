package com.example.animalfacts.domain.usecases

import com.example.animalfacts.data.repository.FactRepository
import com.example.animalfacts.domain.model.Fact
import com.example.animalfacts.domain.model.asFact
import kotlinx.coroutines.flow.first
import java.io.IOException

class LoadFavouriteFactsUseCase(private val repository: FactRepository) {

    suspend operator fun invoke(): Result<List<Fact>> {
        return try {
            val facts = repository.getAllFacts().first()
            Result.success(facts.map { it.asFact() })
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}