package com.example.wiprodemo.domain.repositoryImpl

import com.example.wiprodemo.data.api.ApiService
import com.example.wiprodemo.data.model.UserDto
import com.example.wiprodemo.data.repository.UserRepository
import com.example.wiprodemo.domain.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): UserRepository{

    override fun getUser(): Flow<User> = flow {
        delay(1000)
        emit(
            User(
                id = 751,
                name = "Ravindra",
                email = "ravindra@gmail.com"
            )
        )
    }

    override suspend fun getUsers(): List<UserDto> {
        return apiService.getUsers()
    }
}