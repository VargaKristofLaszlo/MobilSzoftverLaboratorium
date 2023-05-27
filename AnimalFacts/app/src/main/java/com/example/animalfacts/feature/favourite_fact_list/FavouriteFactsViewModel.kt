package com.example.animalfacts.feature.favourite_fact_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalfacts.domain.model.Fact
import com.example.animalfacts.domain.usecases.Favourites.FavouriteFactUseCases
import com.example.animalfacts.ui.model.asFactUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteFactsViewModel @Inject constructor (private val factOpertions: FavouriteFactUseCases): ViewModel() {
    private val _state = MutableStateFlow(FavouriteFactsState())
    val state = _state.asStateFlow()

    init {
        loadFacts()
    }

    private fun loadFacts() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            // Loading from db
            try {
                CoroutineScope(coroutineContext).launch {
                    val facts = factOpertions.loadFavouriteFacts().getOrThrow().map {
                        it.asFactUi()
                    }
                    _state.update { it.copy(isLoading = false, facts = facts) }

                }
            }
            catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e) }
            }
        }
    }

    fun removeFromFavourites(fact: Fact) {
        viewModelScope.launch {
            try{
                CoroutineScope(coroutineContext).launch {
                    factOpertions.removeFavouriteFact(fact._id)

                    val facts = factOpertions.loadFavouriteFacts().getOrThrow().map {
                            it.asFactUi()
                    }
                    _state.update { it.copy(isLoading = false, facts = facts) }

                }
            }
            catch (e: Exception){
                _state.update { it.copy(isLoading = false, error = e) }
            }

        }
    }
}