package com.example.animalfacts.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.animalfacts.R

@Composable
fun BottomBar(navController: NavController) {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Home, stringResource(id = R.string.home))
        },
            label = { Text(text = stringResource(id = R.string.home)) },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
                navController.navigate(Screen.Facts.route )
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Favorite, stringResource(id = R.string.favourite))
        },
            label = { Text(text = stringResource(id = R.string.favourite)) },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
                navController.navigate(Screen.FavouriteFacts.route)
            })
    }
}