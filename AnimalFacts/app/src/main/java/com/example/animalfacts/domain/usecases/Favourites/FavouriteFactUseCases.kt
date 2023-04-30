package com.example.animalfacts.domain.usecases.Favourites

import com.example.animalfacts.data.repository.FactRepository
import javax.inject.Inject

class FavouriteFactUseCases @Inject constructor(repository: FactRepository) {

    val loadFavouriteFacts = LoadFavouriteFactsUseCase(repository)
    val loadFavouriteFact = LoadFavouriteFactUseCase(repository)
    val saveFavouriteFact = SaveFavouriteFactUseCase(repository)
    val removeFavouriteFact = RemoveFavouriteFactUseCase(repository)
}