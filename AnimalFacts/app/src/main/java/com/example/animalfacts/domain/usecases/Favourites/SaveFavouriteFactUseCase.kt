package com.example.animalfacts.domain.usecases.Favourites

import com.example.animalfacts.data.repository.FactRepository
import com.example.animalfacts.domain.model.Fact
import com.example.animalfacts.domain.model.asFactEntity

class SaveFavouriteFactUseCase(private val repository: FactRepository) {

    suspend operator fun invoke(fact: Fact) {
        repository.insertFact(fact.asFactEntity())
    }
}