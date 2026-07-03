package org.example.project.ui.main


import org.example.project.model.Result

data class AppState(
    val isLoading: Boolean = false,
    val results : List<Result> = emptyList(),
    val error: String? = null
)