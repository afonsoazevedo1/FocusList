package com.example.focuslist.ui.feature.addEdit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.focuslist.data.TodoRepository
import com.example.focuslist.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditViewModel(
    private val id: Long? = null,
    private val repository: TodoRepository
) : ViewModel() {
    var title by mutableStateOf("")
        private set

    var description by mutableStateOf<String?>(null)
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        id?.let {
            viewModelScope.launch {
                val todo = repository.getBy(it)
                    title = todo?.title ?: ""
                    description = todo?.description
            }
        }
    }

    fun onEvent(event: AddEditvent) {
        when (event) {
            is AddEditvent.TitleChanged -> {
                title = event.title
            }

            is AddEditvent.DescriptionChanged -> {
                description = event.description
            }

            is AddEditvent.Save -> {
                saveTodo()
            }
        }
    }

        private fun saveTodo() {
            viewModelScope.launch {

                if(title.isBlank()) {
                    _uiEvent.send(UIEvent.ShowSnackbar("Title cannot be empty"))
                }

                repository.insert(title, description, id)
                _uiEvent.send(UIEvent.NavigateBack)
            }

        }
    }
