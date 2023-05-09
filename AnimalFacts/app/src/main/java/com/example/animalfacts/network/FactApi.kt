package com.example.animalfacts.network

import com.example.animalfacts.domain.model.Fact
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FactApi {

    @GET("facts/random")
    suspend fun getFacts(@Query("animal_type") animal_type: String,@Query("amount") amount: Int): List<Fact>

    @GET("facts/{id}")
    suspend fun getFactById(@Path("id") id: String): Fact
}