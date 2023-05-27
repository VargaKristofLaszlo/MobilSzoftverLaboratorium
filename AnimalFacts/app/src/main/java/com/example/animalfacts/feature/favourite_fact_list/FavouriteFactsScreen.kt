package com.example.animalfacts.feature.favourite_fact_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.animalfacts.R
import com.example.animalfacts.domain.model.Fact
import com.example.animalfacts.feature.fact_list.SearchBar
import com.example.animalfacts.navigation.BottomBar
import com.example.animalfacts.ui.model.asFact
import com.example.animalfacts.ui.model.toUiText

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalMaterial3Api
@Composable
fun FavouriteFactsScreen(
    navController: NavController,
    onListItemClick: (String) -> Unit,
    viewModel: FavouriteFactsViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomBar(navController) }
    ) {
        Column() {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(it)) { }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(
                        color = if (!state.isLoading && !state.isError) {
                            MaterialTheme.colorScheme.secondaryContainer
                        } else {
                            MaterialTheme.colorScheme.background
                        }
                    )
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.secondaryContainer
                    )
                } else if (state.isError) {
                    Text(
                        text = state.error?.toUiText()?.asString(context)
                            ?: stringResource(id = R.string.database_error_message)
                    )
                } else {
                    if (state.facts.isEmpty()) {
                        Text(text = stringResource(id = R.string.empty_list))
                    } else {
                        LazyColumn {
                            itemsIndexed(
                                items = state.facts,
                                key = { index,item -> item.hashCode() }
                            ) {index,item ->
                                val dissmissState = rememberDismissState(
                                    confirmStateChange =  {
                                        if (it == DismissValue.DismissedToStart){
                                            viewModel.removeFromFavourites(item.asFact())
                                        }
                                        true
                                    }
                                )

                                SwipeToDismiss(
                                    state = dissmissState,
                                    background = {},
                                    dismissContent = {
                                        com.example.animalfacts.feature.fact_list.FactItemView(fact = item.asFact(), onListItemClick = onListItemClick)
                                    })

                                if (state.facts.indexOf(item) != state.facts.lastIndex) {
                                    Divider(
                                        thickness = 2.dp,
                                        color = MaterialTheme.colorScheme.secondaryContainer
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FactItemView(fact: Fact, onListItemClick: (String) -> Unit) {
    ListItem(
        headlineText = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {  }) {
                Text(text =  fact.text)
            }
        },
        modifier = Modifier.clickable(onClick = { onListItemClick(fact._id) }),
    )
}