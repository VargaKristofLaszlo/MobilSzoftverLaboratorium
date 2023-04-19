package com.example.animalfacts.domain.usecases

import com.example.animalfacts.ui.model.Fact
import com.example.animalfacts.ui.model.asFactEntity

class SaveFavouriteFactUseCase(private val repository: FactRepository) {

    suspend operator fun invoke(fact: Fact) {
        repository.insertFact(fact.asFactEntity())
    }
}