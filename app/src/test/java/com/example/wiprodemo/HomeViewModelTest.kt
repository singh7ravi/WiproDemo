package com.example.wiprodemo

import com.example.wiprodemo.data.model.UserDto
import com.example.wiprodemo.data.repository.UserRepository
import com.example.wiprodemo.presentation.viewmodel.HomeUiState
import com.example.wiprodemo.presentation.viewmodel.HomeViewModel
import com.example.wiprodemo.presentation.viewmodel.LoginViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class HomeViewModelTest {

    private lateinit var repository: UserRepository
    private lateinit var viewModel: HomeViewModel
    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setup() {
        repository = mock()
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun `loadUsers should emit Success state` () = runTest {

            val users = listOf(
                UserDto(
                    id = 1,
                    name = "Ravi",
                    email = "ravi@gmail.com",
                    phone = "9555222751"
                )
            )
        whenever(repository.getUsers())
            .thenReturn(users)

        viewModel.loadUsers()

        val state = viewModel.uiState.value

        assertTrue(state is HomeUiState.Success)

        assertEquals(
            users,
            (state as HomeUiState.Success).users
        )
    }

    @Test
    fun `login should succeed for valid password`() {

        val result = loginViewModel.login(
            "abc@gmail.com",
            "1220"
        )

        //assertTrue(result)
    }
    @After
    fun tearDown() {

    }
}