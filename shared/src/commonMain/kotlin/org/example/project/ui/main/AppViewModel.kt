package org.example.project.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.domain.IPingerService



class AppViewModel(
    private val pingerService : IPingerService
) : ViewModel() {
    private val _state = MutableStateFlow(AppState())
    val state = _state.asStateFlow()

    fun launch() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                println("try")
                val result = pingerService.ping()
                _state.update { it.copy(isLoading = false, results = result) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message ?: "Unknown error") }

            }
        }
    }

}