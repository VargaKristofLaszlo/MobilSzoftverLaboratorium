package com.example.animalfacts.network

import com.example.animalfacts.domain.model.Fact
import javax.inject.Inject
import retrofit2.Callback;
import java.util.*

class FactServiceNetworkImpl @Inject constructor(private  val factApi: FactApi) : FactService{
    override suspend fun getFacts(animal_type: String, amount: Int): List<Fact> {
        return factApi.getFacts(animal_type, amount)
    }

    override suspend fun getFactById(id: String): Fact {
        return factApi.getFactById(id)
    }

}