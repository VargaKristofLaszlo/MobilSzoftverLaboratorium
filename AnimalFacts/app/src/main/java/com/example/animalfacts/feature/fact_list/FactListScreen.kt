package com.example.animalfacts.feature.fact_list

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.animalfacts.R
import com.example.animalfacts.domain.model.Fact
import com.example.animalfacts.navigation.BottomBar
import com.example.animalfacts.ui.NormalTextField
import com.example.animalfacts.ui.common.NumberTextField
import com.example.animalfacts.ui.model.asFact
import com.example.animalfacts.ui.model.toUiText
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterialApi::class)
@ExperimentalMaterial3Api
@Composable
fun FactListScreen(
    navController: NavController,
    onListItemClick: (String) -> Unit,
    viewModel: FactViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomBar(navController) }
    ) {
        Column() {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(it)) {
                SearchBar(viewModel)
            }
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
                            ?: stringResource(id = R.string.network_error_message)
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
                                        if (it == DismissValue.DismissedToEnd){
                                            viewModel.addToFavourites(item.asFact())
                                        }
                                        true
                                    }
                                )
                                
                                SwipeToDismiss(
                                    state = dissmissState,
                                    background = {},
                                    dismissContent = {
                                        FactItemView(fact = item.asFact(), onListItemClick = onListItemClick)
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(viewModel: FactViewModel){
    val keyboardController = LocalSoftwareKeyboardController.current
    var animalType by remember { mutableStateOf("cat")}
    var amount by remember { mutableStateOf(5)}

    Column() {
        Row() {
            NormalTextField(
                value = animalType,
                label = stringResource(id = R.string.animal),
                onValueChange = { newText -> animalType = newText},
                singleLine = true,
                onDone = { keyboardController?.hide()  },
                modifier = Modifier
                    .fillMaxWidth(0.45f)
                    .padding(5.dp, 5.dp)
            )
            NumberTextField(
                value = amount.toString(),
                label =  stringResource(id = R.string.amount),
                onValueChange = { newText -> amount = newText.toInt()},
                singleLine = true,
                onDone = { keyboardController?.hide() },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(5.dp, 5.dp)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                viewModel.filter.value.animalType = animalType
                viewModel.filter.value.amount = amount
                viewModel.loadFacts()
            }
            ) {
                Text(text = stringResource(R.string.search))
            }
        }
    }
}