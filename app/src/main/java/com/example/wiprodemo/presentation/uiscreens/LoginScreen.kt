package com.example.wiprodemo.presentation.uiscreens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow


@Composable
fun LoginScreen(
    onNavigateToHome: (Int, String) -> Unit,
    result : String?
) {
    var login by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    val mContext = LocalContext.current

    LaunchedEffect(result) {
        if (!result.isNullOrBlank()) {
            Toast.makeText(mContext, result,
                Toast.LENGTH_SHORT).show()
        }
    }

    Column(modifier = Modifier.fillMaxSize()
        .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center) {

        Text(
            text = "Welcome You",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Sign in to continue to your account.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = login,
            onValueChange = {
                login = it
            }, modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Enter Name")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Blue,
                unfocusedTextColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Blue,
                unfocusedTextColor = Color.Black
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation =
                if(passwordVisible)
                 VisualTransformation.None
                else
                 PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisible = !passwordVisible
                }) {
                    if(passwordVisible)
                        Icon(imageVector = Icons.Default.AddCircle, contentDescription = "icon")
                    else
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "icon")
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
                if(password == "1220"){
                    onNavigateToHome(101, login)
                }
        }, modifier = Modifier.fillMaxWidth()
            .height(52.dp)) {
            Text("Login")
        }

    }



}
