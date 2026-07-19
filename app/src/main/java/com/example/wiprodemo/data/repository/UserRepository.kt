package com.example.wiprodemo.data.repository

import com.example.wiprodemo.data.model.UserDto
import com.example.wiprodemo.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<User>
    suspend fun getUsers() : List<UserDto>
}