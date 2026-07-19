package com.example.wiprodemo.di

import com.example.wiprodemo.data.api.ApiService
import com.example.wiprodemo.data.repository.UserRepository
import com.example.wiprodemo.domain.repositoryImpl.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): UserRepository{
        return UserRepositoryImpl(apiService)
    }
}