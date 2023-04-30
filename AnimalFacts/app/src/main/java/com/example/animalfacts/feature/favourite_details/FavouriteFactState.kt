package com.example.animalfacts.feature.favourite_details

import com.example.animalfacts.ui.model.FactUi

data class FavouriteFactState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val facts: FactUi? = null
)