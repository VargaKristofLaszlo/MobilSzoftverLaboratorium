package com.example.animalfacts.ui.theme.facts.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class FactDetailViewModel @Inject constructor(
    private  val factDetailRepository: FactDetailRepository
) : ViewModel() {
    private val factIdSharedFlow: MutableSharedFlow<String> = MutableSharedFlow(replay = 1)

    val factDetailFlow = factIdSharedFlow.flatMapLatest {
        factDetailRepository.getFactById(it)
    }

    init {
    }

    fun loadFactById(id: String) = factIdSharedFlow.tryEmit(id)
}