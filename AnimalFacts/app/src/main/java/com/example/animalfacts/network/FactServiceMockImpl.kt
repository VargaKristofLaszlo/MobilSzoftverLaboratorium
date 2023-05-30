package com.example.animalfacts.network

import com.example.animalfacts.domain.model.Fact

class FactServiceMockImpl : FactService{
    private val fact1: Fact = Fact("fact1",1,"teszt1",false,0,"cat")
    private val fact2: Fact = Fact("fact2",1,"teszt2",false,0,"cat")


    override suspend fun getFacts(animal_type: String, amount: Int): List<Fact> {
        return listOf(fact1,fact2)
    }

    override suspend fun getFactById(id: String): Fact {
        return fact1
    }

}