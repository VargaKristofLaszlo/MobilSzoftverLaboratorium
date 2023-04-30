package com.example.animalfacts.feature.fact_details

import com.example.animalfacts.ui.model.FactUi

data class FactDetailState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val todos: FactUi? = null
)