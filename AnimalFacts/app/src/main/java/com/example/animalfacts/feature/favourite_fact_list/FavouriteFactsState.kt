package com.example.animalfacts.feature.favourite_fact_list

import com.example.animalfacts.ui.model.FactUi

data class FavouriteFactsState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val facts: List<FactUi> = emptyList()
)