package com.example.animalfacts.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animalfacts.feature.fact_details.FactDetailsScreen
import com.example.animalfacts.feature.fact_list.FactListScreen
import com.example.animalfacts.feature.favourite_details.FavouriteFactDetailsScreen
import com.example.animalfacts.feature.favourite_fact_list.FavouriteFactsScreen

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
            FactListScreen(
                onListItemClick = {
                    navController.navigate(Screen.FactDetails.passId(it))
                }
            )
        }
        composable(Screen.FactDetails.route) {
            FactDetailsScreen(onNavigateBack = {
                navController.popBackStack(
                    route = Screen.Facts.route,
                    inclusive = true
                )
                navController.navigate(Screen.Facts.route)
            })
        }
        composable(Screen.FavouriteFacts.route) {
            FavouriteFactsScreen(
                onListItemClick = {
                    navController.navigate(Screen.FavouriteFactDetails.passId(it))
                }
            )
        }
        composable(Screen.FavouriteFactDetails.route) {
            FavouriteFactDetailsScreen(onNavigateBack = {
                    navController.popBackStack(
                        route = Screen.FavouriteFacts.route,
                        inclusive = true
                    )
                    navController.navigate(Screen.FavouriteFacts.route)
                }
            )
        }
    }
}