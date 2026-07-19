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
class HomeViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel(){

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
       val uiState: StateFlow<HomeUiState> = _uiState


    init {
      //  initLoading()
        loadUsers()
    }
    fun initLoading(){
        viewModelScope.launch {
            repository.getUser().collect { user ->
               // _uiState.value = HomeUiState.Success(user)
            }
        }
    }

    fun loadUsers(){
        viewModelScope.launch {
            try {
                val response = repository.getUsers()
                _uiState.value = HomeUiState.Success(response)
            }catch (e: Exception){
               _uiState.value = HomeUiState.Error("Unknown Error")
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}