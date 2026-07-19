package com.example.wiprodemo.presentation.uiscreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wiprodemo.presentation.viewmodel.HomeViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.wiprodemo.presentation.viewmodel.HomeUiState

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import coil.compose.AsyncImage

private const val RESULT_MESSAGE = "User return from home screen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    id: Int,
    name: String,
    onBackClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Home Screen")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackClick("User Return from Home Screen")
                    }) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = "Welcomes $name \uD83D\uDC4B",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )



            Spacer(modifier = Modifier.height(24.dp))

            Card(modifier = Modifier.fillMaxWidth()) {

                Text(
                    text = "User details",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "ID",
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = id.toString())
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "UserName",
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = name)
                }
            }
            Spacer(modifier = Modifier.size(20.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onBackClick(RESULT_MESSAGE)
                }
            ) {
                Text("Go Back")
            }
            Spacer(modifier = Modifier.size(20.dp))

            when (uiState) {
                is HomeUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is HomeUiState.Success -> {
                    val users =  (uiState as HomeUiState.Success).users

                    LazyColumn {
                        items(users){ user ->

                            Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                               Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
                                   verticalAlignment = Alignment.CenterVertically) {

                                   AsyncImage(
                                       model = "https://i.pravatar.cc/150?img=${user.id}",
                                       contentDescription = "user_icon",
                                       modifier = Modifier.size(60.dp)
                                           .clip(CircleShape)
                                   )
                                   Spacer(modifier = Modifier.height(16.dp))
                                   Column(
                                       modifier = Modifier.padding(16.dp)
                                   ) {
                                       Text(text = "${user.id}", color = Color.White)
                                       Text(text = "${user.name}", color = Color.White)
                                       Text(text = "${user.email}", color = Color.White)
                                   }
                               }
                            }

                        }
                    }
                }
                is HomeUiState.Error -> {
                    val userError =  (uiState as HomeUiState.Error).errorMsg

                    Text(text = userError, color = Color.Red)
                }
            }


        }
    }
}