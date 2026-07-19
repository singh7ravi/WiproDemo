package com.example.wiprodemo.presentation.viewmodel

import com.example.wiprodemo.data.model.UserDto
import com.example.wiprodemo.domain.model.User

/*
sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(val user: User): HomeUiState()
    data class Error(val errorMsg: String): HomeUiState()
}*/


sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(val users: List<UserDto>) : HomeUiState()
    data class Error (val errorMsg : String) : HomeUiState()
}