package com.example.animalfacts.feature.favourite_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalfacts.domain.usecases.Favourites.FavouriteFactUseCases
import com.example.animalfacts.feature.favourite_fact_list.FavouriteFactsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavouriteFactViewModel(private val factOpertions: FavouriteFactUseCases): ViewModel()  {  private val _state = MutableStateFlow(
    FavouriteFactsState()
)
    val state = _state.asStateFlow()

    init {
        loadFact()
    }

    private  fun loadFact() {
        viewModelScope.launch {
            // TODO: Load fact
        }
    }
}