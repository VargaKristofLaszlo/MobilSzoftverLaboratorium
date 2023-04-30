package com.example.animalfacts.feature.fact_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalfacts.domain.usecases.facts.FactUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor (private val factOpertions: FactUseCases): ViewModel() {
    private val _state = MutableStateFlow(FactsState())
    val state = _state.asStateFlow()

    init {
        loadFacts()
    }

    private  fun loadFacts() {
        viewModelScope.launch {
            // TODO: Load facts
        }
    }
}