package com.example.animalfacts.domain.usecases.Favourites

import com.example.animalfacts.data.repository.FactRepository
import com.example.animalfacts.domain.model.Fact
import com.example.animalfacts.domain.model.asFactEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlin.math.log

class SaveFavouriteFactUseCase(private val repository: FactRepository) {

    suspend operator fun invoke(fact: Fact) {
            repository.insertFact(fact.asFactEntity())
    }
}