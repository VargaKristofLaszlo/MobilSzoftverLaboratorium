package com.example.animalfacts.feature.fact_list

import com.example.animalfacts.ui.model.FactUi

data class FactsState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val facts: List<FactUi> = emptyList()
)