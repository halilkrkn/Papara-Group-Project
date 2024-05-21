package com.halilkrkn.chatchef.presentation.LoginPage

import android.widget.Toast
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.*

import androidx.lifecycle.viewmodel.compose.viewModel
import com.halilkrkn.chatchef.presentation.LoginPage.viewModel.ForgotPasswordViewModel
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.halilkrkn.chatchef.R
import com.halilkrkn.chatchef.presentation.components.ButtonComponent
import com.halilkrkn.chatchef.presentation.components.Loader
import com.halilkrkn.chatchef.presentation.components.TextFieldComponent

@Composable
fun ForgotPasswordScreen(navController: NavHostController, viewModel: ForgotPasswordViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current


    LaunchedEffect(uiState.error) {
        uiState.error?.let { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }


    LaunchedEffect(uiState.transactionState) {
        if (uiState.transactionState) {
            Toast.makeText(context, "Email Sent, Check Your Email", Toast.LENGTH_SHORT).show()
            navController.navigate("login") {
                popUpTo("login") {
                    inclusive = true
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.padding(18.dp)
    ) {
        if (uiState.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Loader(resId = R.raw.loading_anim)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Loader(resId = R.raw.forgot_password_anim, height = 260.dp)

                Spacer(modifier = Modifier.height(50.dp))

                TextFieldComponent(
                    email,
                    onValueChange = { updatedEmail ->
                        email = updatedEmail
                    },
                    label = "Email",
                    painterResource = painterResource(id = R.drawable.mail_icon)
                )

                Spacer(modifier = Modifier.height(30.dp))

                Spacer(modifier = Modifier.height(50.dp))

                ButtonComponent(value = "Reset Password", onClick = {
                    viewModel.sendPasswordResetEmail(email)
                })
            }
        }
    }
}
