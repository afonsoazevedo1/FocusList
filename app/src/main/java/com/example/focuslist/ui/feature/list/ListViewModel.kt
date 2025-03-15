package com.example.focuslist.ui.feature.list

import AddEditRoute
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.focuslist.data.TodoRepository
import com.example.focuslist.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    val todos = repository.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.Delete -> {
                delete(event.id)
            }
            is ListEvent.CompleteChanged -> {
                completeChanged(event.id, event.isCompleted)
            }
            is ListEvent.AddEdit -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.Navigate(AddEditRoute(event.id)))
                }
            }
        }
    }

    private fun delete(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
        }
    }

    private fun completeChanged(id: Long, isCompleted: Boolean) {
        viewModelScope.launch {
            repository.updateCompleted(id, isCompleted)
        }
    }

//    private fun addEdit(id: Long?) {
//        viewModelScope.launch {
//            repository.addEdit(id)
//        }
//    }
}