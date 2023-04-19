package com.example.animalfacts.domain.usecases

import com.example.animalfacts.data.repository.FactRepository
import com.example.animalfacts.domain.model.Fact
import com.example.animalfacts.domain.model.asFact
import kotlinx.coroutines.flow.first
import java.io.IOException

class LoadFavouriteFactUseCase(private val repository: FactRepository) {
    suspend operator fun invoke(id: String): Result<Fact> {
        return try {
            Result.success(repository.getFactById(id).first().asFact())
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}