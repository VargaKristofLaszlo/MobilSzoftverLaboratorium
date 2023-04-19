package com.example.animalfacts.feature.favourite_fact_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalfacts.domain.usecases.Favourites.FavouriteFactUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavouriteFactViewModel(private val factOpertions: FavouriteFactUseCases): ViewModel() {
    private val _state = MutableStateFlow(FavouriteFactsState())
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