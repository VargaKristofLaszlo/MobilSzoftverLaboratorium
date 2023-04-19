package com.example.animalfacts.feature.favourite_fact_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalfacts.domain.usecases.Favourites.FavouriteFactUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteFactsViewModel @Inject constructor (factOpertions: FavouriteFactUseCases): ViewModel() {
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