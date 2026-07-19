package com.example.wiprodemo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wiprodemo.presentation.uiscreens.HomeScreen
import com.example.wiprodemo.presentation.uiscreens.LoginScreen
import kotlinx.coroutines.flow.Flow

@Composable
fun AppNavHost(
    navHostController: NavHostController) {

    NavHost(navController = navHostController,
        startDestination = Screen.Login.route,
        modifier = Modifier){

        composable(Screen.Login.route){ backStackEntry ->
            val saveStateHandle = backStackEntry.savedStateHandle

            val result = saveStateHandle.getStateFlow("result", "").collectAsState()
            LoginScreen(
                onNavigateToHome = { id, name ->
                    navHostController.navigate(Screen.Home.createRoute(id, name))
                }, result = result.value
            )
            backStackEntry.savedStateHandle.remove<String>("result")
        }

        composable(Screen.Home.route) {

            val id = it.arguments?.getString("id")?.toInt() ?: 0
            val name = it.arguments?.getString("name").orEmpty()
            HomeScreen(
                id = id,
                name = name,
                onBackClick = { message ->

                    navHostController.
                    previousBackStackEntry?.
                    savedStateHandle?.
                    set("result", message)

                    navHostController.popBackStack()
                }
            )
        }
    }
}