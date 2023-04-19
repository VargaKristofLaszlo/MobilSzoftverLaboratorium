package com.example.animalfacts.ui.theme.facts.detail

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FactDetailRepository {

    fun getFactById(id: String) = flow<String> {  }.flowOn(Dispatchers.IO)
}