package com.example.animalfacts.feature.favourite_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalfacts.domain.usecases.Favourites.FavouriteFactUseCases
import com.example.animalfacts.feature.favourite_fact_list.FavouriteFactsState
import com.example.animalfacts.ui.model.FactUi
import com.example.animalfacts.ui.model.asFactUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteFactViewModel @Inject constructor (private val factOpertions: FavouriteFactUseCases, savedStateHandle: SavedStateHandle): ViewModel()  {  private val _state = MutableStateFlow(
    FavouriteFactsState()
)
    val state = _state.asStateFlow()
    private val factId: String = checkNotNull(savedStateHandle["id"])

    init {
        loadFact()
    }

    private  fun loadFact() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            // Loading from db
            try {
                CoroutineScope(coroutineContext).launch {
                    val facts = listOf<FactUi>(factOpertions.loadFavouriteFact(factId).getOrThrow().asFactUi())
                    _state.update { it.copy(isLoading = false, facts = facts) }

                }
            }
            catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e) }
            }
        }
    }
}