package com.example.wiprodemo.presentation.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wiprodemo.data.repository.UserRepository
import com.example.wiprodemo.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel(){

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
       val uiState: StateFlow<HomeUiState> = _uiState


    init {
        login("Ravi@gmail.com", "1220")
    }

    fun login(
        email: String,
        password: String
    ){

    }

    override fun onCleared() {
        super.onCleared()
    }
}