package com.example.focuslist.ui.feature.addEdit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.focuslist.data.TodoDataProvider
import com.example.focuslist.data.TodoDatabase
import com.example.focuslist.data.TodoRepositoryImpl
import com.example.focuslist.ui.UIEvent
import com.example.focuslist.ui.theme.FocusListTheme


@Composable
fun AddEditScreen(
    id: Long?,
    navigateBack: () -> Unit,
) {

    val context = LocalContext.current.applicationContext
    val database = TodoDataProvider.provide(context)
    val repository = TodoRepositoryImpl(
        dao = database.todoDao
    )

    val viewModel = viewModel<AddEditViewModel> {
        AddEditViewModel(
            id = id,
            repository = repository
        )
    }

    val title = viewModel.title
    val description = viewModel.description

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when(uiEvent) {
                is UIEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = uiEvent.message
                    )
                }

                UIEvent.NavigateBack -> {
                   navigateBack()
                }

                is UIEvent.Navigate<*> -> {
                    TODO()
                }
            }
        }
    }

    AddEditContent(
        title,
        description,
        snackbarHostState,
        viewModel::onEvent
    )
}

@Composable
fun AddEditContent(
    title: String,
    description: String?,
    snackbarHostState: SnackbarHostState,
    onEvent: (AddEditvent) -> Unit,
) {
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(AddEditvent.Save)
            }
            ) {
                Icon(Icons.Default.Check, contentDescription = "Salvar")
            }
        },

        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }

    ) {
        Column (
            modifier = Modifier.
                consumeWindowInsets(it)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = {
                    onEvent(
                        AddEditvent.TitleChanged(it)
                    )
                },
                placeholder = {
                    Text(text = "Título")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = description ?: "",
                onValueChange = {
                    onEvent(
                        AddEditvent.DescriptionChanged(it)
                    )
                },
                placeholder = {
                    Text(text = "Descrição")
                }
            )
        }
    }
}

@Preview
@Composable
private fun AddEditContentPreview() {
    FocusListTheme {
        AddEditContent(
            title = "",
            description = null,
            snackbarHostState = SnackbarHostState(),
            onEvent = {}
        )
    }
}