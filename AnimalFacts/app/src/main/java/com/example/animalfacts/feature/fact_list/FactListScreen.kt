package com.example.animalfacts.feature.fact_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.animalfacts.ui.model.toUiText
import com.example.animalfacts.R

@ExperimentalMaterial3Api
@Composable
fun FactListScreen(
    onListItemClick: (String) -> Unit,
    viewModel: FactViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
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
            contentAlignment = Alignment.Center
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
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(0.98f)
                            .padding(it)
                            .clip(RoundedCornerShape(5.dp))
                    ) {
                        items(state.facts.size) { i ->
                            ListItem(
                                headlineText = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(text = state.facts[i].text)
                                    }
                                },
                                modifier = Modifier.clickable(onClick = { onListItemClick(state.facts[i]._id) })
                            )
                            if (i != state.facts.lastIndex) {
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