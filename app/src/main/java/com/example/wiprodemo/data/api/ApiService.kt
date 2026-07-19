package com.example.wiprodemo.data.api

import com.example.wiprodemo.data.model.UserDto
import com.example.wiprodemo.domain.model.User
import retrofit2.http.GET


interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<UserDto>
}