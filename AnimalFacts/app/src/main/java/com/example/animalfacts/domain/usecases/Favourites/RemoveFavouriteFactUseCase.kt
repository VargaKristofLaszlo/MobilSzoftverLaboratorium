package com.example.animalfacts.domain.usecases.Favourites

import com.example.animalfacts.data.repository.FactRepository

class RemoveFavouriteFactUseCase(private val repository: FactRepository) {

    suspend operator fun invoke(id: String) {
        repository.deleteFact(id)
    }
}