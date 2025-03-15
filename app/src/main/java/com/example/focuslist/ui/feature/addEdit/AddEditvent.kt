package com.example.focuslist.ui.feature.addEdit

sealed interface AddEditvent {
    data class TitleChanged(val title: String): AddEditvent
    data class DescriptionChanged(val description: String?): AddEditvent
    data object Save: AddEditvent
}