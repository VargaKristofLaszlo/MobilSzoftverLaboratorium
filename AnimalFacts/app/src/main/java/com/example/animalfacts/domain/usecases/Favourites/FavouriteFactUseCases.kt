package com.example.animalfacts.domain.usecases.Favourites

import com.example.animalfacts.data.repository.FactRepository

class FavouriteFactUseCases(repository: FactRepository){
    val loadFavouriteFacts = LoadFavouriteFactsUseCase(repository)
    val loadFavouriteFact = LoadFavouriteFactUseCase(repository)
    val saveFavouriteFact = SaveFavouriteFactUseCase(repository)
    val removeFavouriteFact = RemoveFavouriteFactUseCase(repository)
}