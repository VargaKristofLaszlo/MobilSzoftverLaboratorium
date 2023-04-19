package com.example.animalfacts.domain.usecases.facts

import com.example.animalfacts.network.FactService

class FactUseCases(private val service: FactService) {
    val loadFacts = LoadFactsUseCase(service)
    val loadFact = LoadFactUseCase(service)
}