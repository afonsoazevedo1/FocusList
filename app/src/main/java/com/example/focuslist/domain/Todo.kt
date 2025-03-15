package com.example.focuslist.domain

data class Todo(
    val id: Long,
    val title: String,
    val description: String?,
    val isCompleted: Boolean,
)


val todo1 = Todo(
    id = 1,
    title = "ToDo1",
    description = "Descrição1",
    isCompleted = false
)

val todo2 = Todo(
    id = 2,
    title = "ToDo2",
    description = "Descrição2",
    isCompleted = false
)
val todo3 = Todo(
    id = 3,
    title = "ToDo3",
    description = "Descrição3",
    isCompleted = false
)