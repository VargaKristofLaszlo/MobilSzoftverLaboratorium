package com.example.animalfacts.feature.fact_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.DismissValue
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.animalfacts.R
import com.example.animalfacts.feature.fact_list.FactItemView
import com.example.animalfacts.feature.fact_list.SearchBar
import com.example.animalfacts.navigation.BottomBar
import com.example.animalfacts.ui.NormalTextField
import com.example.animalfacts.ui.model.asFact
import com.example.animalfacts.ui.model.toUiText

@ExperimentalMaterial3Api
@Composable
fun FactDetailsScreen(
    navController: NavController,
    onNavigateBack: (String) -> Unit,
    viewModel: FactDetailViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize().fillMaxWidth(),
        bottomBar = { BottomBar(navController) },
    ) {
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
                    ),
            horizontalAlignment = Alignment.CenterHorizontally,
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
                            NormalTextField(value = state.facts[0].animal_type, label = stringResource(
                                                            R.string.animal_type), onValueChange = {}, onDone = {})

                            NormalTextField(value = state.facts[0].text, label = stringResource(R.string.fact), onValueChange = {}, onDone ={} )
                        }

                }
            }
        }
    }