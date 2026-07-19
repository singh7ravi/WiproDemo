package com.example.wiprodemo.presentation.navigation

sealed class Screen(val route: String){
    data object Login: Screen("Login")
    data object Home : Screen("home/{id}/{name}"){
        fun createRoute(
            id: Int,
            name: String
        ) = "home/$id/$name"
    }
}