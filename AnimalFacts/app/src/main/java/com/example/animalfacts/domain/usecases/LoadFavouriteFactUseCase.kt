package com.example.animalfacts.domain.usecases

import com.example.animalfacts.ui.model.Fact
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