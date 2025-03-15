package com.example.focuslist.ui.feature.list

import AddEditRoute
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.focuslist.data.TodoDataProvider
import com.example.focuslist.data.TodoRepositoryImpl
import com.example.focuslist.domain.Todo
import com.example.focuslist.domain.todo1
import com.example.focuslist.domain.todo2
import com.example.focuslist.domain.todo3
import com.example.focuslist.ui.UIEvent
import com.example.focuslist.ui.components.TodoItem
import com.example.focuslist.ui.feature.addEdit.AddEditViewModel
import com.example.focuslist.ui.theme.FocusListTheme

@Composable
fun ListScreen(
    navigateToAddEditScreen: (id: Long?) -> Unit,
) {

    val context = LocalContext.current.applicationContext
    val database = TodoDataProvider.provide(context)
    val repository = TodoRepositoryImpl(
        dao = database.todoDao
    )

    val viewModel = viewModel<ListViewModel> {
        ListViewModel(repository = repository)
    }

    val todos by viewModel.todos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when(uiEvent) {
                is UIEvent.Navigate<*> -> {
                    when (uiEvent.route) {
                    is AddEditRoute -> {
                        navigateToAddEditScreen(uiEvent.route.id)
                    }
                    }
                }

                UIEvent.NavigateBack -> TODO()
                is UIEvent.ShowSnackbar -> TODO()
            }
    }
    }

    ListContent(
        todos = todos,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ListContent(
    todos: List<Todo>,
    onEvent: (ListEvent) -> Unit, ) {
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ListEvent.AddEdit(null))
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
    LazyColumn (
        modifier = Modifier
            .consumeWindowInsets(it),
        contentPadding =
            PaddingValues(16.dp)
        ) {
        itemsIndexed(todos) { index, todo ->
            TodoItem(
                todo = todo,
                onCompletedChange = {
                    onEvent(ListEvent.CompleteChanged(todo.id, it))
                },
                onItemClick = {
                    onEvent(ListEvent.AddEdit(todo.id))
                },
                onDeleteClick = {
                    onEvent(ListEvent.Delete(todo.id))
                },
            )

            if(index < todos.lastIndex) {
                Spacer( modifier = Modifier.height(8.dp))
            }
        }
    }
    }
}

@Preview
@Composable
private fun ListContentPreview() {
    FocusListTheme {
        ListContent(todos = listOf(todo1, todo2, todo3), onEvent = {})
    }
}
