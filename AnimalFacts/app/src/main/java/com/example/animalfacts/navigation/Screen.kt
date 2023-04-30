package com.example.animalfacts.navigation

sealed class Screen(val route: String) {
    object Facts: Screen("facts")
    object FactDetails: Screen("facts/{id}") {
        fun passId(id: String) = "facts/$id"
    }
    object FavouriteFacts: Screen("favourites")
    object FavouriteFactDetails: Screen("favourites/{id}") {
        fun passId(id: String) = "favourites/$id"
    }
}