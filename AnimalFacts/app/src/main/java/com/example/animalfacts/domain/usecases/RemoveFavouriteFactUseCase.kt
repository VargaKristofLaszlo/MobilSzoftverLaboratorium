package com.example.animalfacts.domain.usecases

class RemoveFavouriteFactUseCase(private val repository: FactRepository) {

    suspend operator fun invoke(id: String) {
        repository.deleteFact(id)
    }
}