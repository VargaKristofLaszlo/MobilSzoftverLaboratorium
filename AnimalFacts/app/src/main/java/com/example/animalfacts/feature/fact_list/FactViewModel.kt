package com.example.animalfacts.feature.fact_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalfacts.domain.model.FactFilter
import com.example.animalfacts.domain.model.asFact
import com.example.animalfacts.domain.usecases.Favourites.FavouriteFactUseCases
import com.example.animalfacts.domain.usecases.facts.FactUseCases
import com.example.animalfacts.ui.model.asFact
import com.example.animalfacts.ui.model.asFactUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor (private val factOpertions: FactUseCases, private val favouriteOperations: FavouriteFactUseCases): ViewModel() {
    private val _state = MutableStateFlow(FactsState())
    val state = _state.asStateFlow()
    val filter: MutableState<FactFilter> = mutableStateOf(FactFilter())

    init {
        loadFacts()
    }

    private  fun loadFacts() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            // Test loading from db
            try {
                CoroutineScope(coroutineContext).launch {
                    val facts = favouriteOperations.loadFavouriteFacts().getOrThrow().map {
                        it.asFactUi()
                    }
                    _state.update { it.copy(isLoading = false, facts = facts) }

                }
            }
            catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e) }
            }
            // Loading data from api
          /*  try {
                CoroutineScope(coroutineContext).launch {
                    val facts = factOpertions.loadFacts(filter.value).getOrThrow().map {
                        it.asFactUi()
                    }
                    _state.update { it.copy(isLoading = false, facts = facts) }

                }
            }
            catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e) }
            }*/
        }
    }

    fun testDbSave() {
        viewModelScope.launch {
            state.value.facts.forEach {
                favouriteOperations.saveFavouriteFact(it.asFact())
            }
        }
    }
}