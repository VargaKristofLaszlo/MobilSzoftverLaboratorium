package com.example.animalfacts.feature.fact_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalfacts.domain.usecases.facts.FactUseCases
import com.example.animalfacts.feature.fact_list.FactsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FactDetailViewModel(private val factOpertions: FactUseCases): ViewModel() {
    private val _state = MutableStateFlow(FactsState())
    val state = _state.asStateFlow()

    init {
        loadFact()
    }

    private  fun loadFact() {
        viewModelScope.launch {
            // TODO: Load facts
        }
    }
}