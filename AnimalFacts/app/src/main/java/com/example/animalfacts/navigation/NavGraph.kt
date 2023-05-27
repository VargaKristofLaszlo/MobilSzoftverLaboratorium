package com.example.animalfacts.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animalfacts.feature.fact_details.FactDetailViewModel
import com.example.animalfacts.feature.fact_details.FactDetailsScreen
import com.example.animalfacts.feature.fact_list.FactListScreen
import com.example.animalfacts.feature.fact_list.FactViewModel
import com.example.animalfacts.feature.favourite_details.FavouriteFactDetailsScreen
import com.example.animalfacts.feature.favourite_details.FavouriteFactViewModel
import com.example.animalfacts.feature.favourite_fact_list.FavouriteFactsScreen
import com.example.animalfacts.feature.favourite_fact_list.FavouriteFactsViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Facts.route
    ) {
        composable(Screen.Facts.route) {
            val viewModel = hiltViewModel<FactViewModel>()
            FactListScreen(
                navController,
                onListItemClick = {
                    navController.navigate(Screen.FactDetails.passId(it))
                },viewModel
            )
        }
        composable(Screen.FactDetails.route) {
            val viewModel = hiltViewModel<FactDetailViewModel>()
            FactDetailsScreen(
                navController,
                onNavigateBack = {
                navController.popBackStack(
                    route = Screen.Facts.route,
                    inclusive = true
                )
                navController.navigate(Screen.Facts.route)
            },viewModel)
        }
        composable(Screen.FavouriteFacts.route) {
            val viewModel = hiltViewModel<FavouriteFactsViewModel>()
            FavouriteFactsScreen(
                navController,
                onListItemClick = {
                    navController.navigate(Screen.FavouriteFactDetails.passId(it))
                },viewModel
            )
        }
        composable(Screen.FavouriteFactDetails.route) {
            val viewModel = hiltViewModel<FavouriteFactViewModel>()
            FavouriteFactDetailsScreen(
                navController,
                onNavigateBack = {
                    navController.popBackStack(
                        route = Screen.FavouriteFacts.route,
                        inclusive = true
                    )
                    navController.navigate(Screen.FavouriteFacts.route)
                },viewModel
            )
        }
    }
}