package com.example.animalfacts.domain.usecases.facts

import com.example.animalfacts.network.FactService
import javax.inject.Inject

class FactUseCases @Inject constructor(service: FactService) {
    val loadFacts = LoadFactsUseCase(service)
    val loadFact = LoadFactUseCase(service)
}