package com.halilkrkn.chatchef.presentation.LoginPage

import AuthViewModel
import android.util.Log
import android.widget.Toast
import androidx.collection.emptyLongSet
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.halilkrkn.chatchef.R
import com.halilkrkn.chatchef.navigation.util.AuthScreen
import com.halilkrkn.chatchef.navigation.util.Graph
import com.halilkrkn.chatchef.presentation.components.ButtonComponent
import com.halilkrkn.chatchef.presentation.components.ClickableLoginTextComponent
import com.halilkrkn.chatchef.presentation.components.DividerTextComponent
import com.halilkrkn.chatchef.presentation.components.Loader
import com.halilkrkn.chatchef.presentation.components.TextFieldComponent
import com.halilkrkn.chatchef.presentation.components.PasswordFieldComponent
import com.halilkrkn.chatchef.presentation.components.UnderLinedTextComponent

@Composable
fun LoginScreen(
    navController: NavHostController,
    onLoginClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    viewModel: AuthViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val loggingState by viewModel.loggingState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.isLoggedIn()
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(uiState.user) {
        uiState.user?.let {
            navController.navigate(Graph.BOTTOMBAR) {
                popUpTo(Graph.BOTTOMBAR) {
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(loggingState.transaction) {
        if (loggingState.transaction) {
            navController.navigate(Graph.BOTTOMBAR) {
                popUpTo(Graph.BOTTOMBAR) {
                    inclusive = true
                }
            }
        }
    }

    if (uiState.isLoading || loggingState.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Loader(resId = R.raw.loading_anim)
        }
    } else {
        Scaffold(modifier = Modifier.padding(18.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Loader(R.raw.login_anim, height = 250.dp)
                Spacer(modifier = Modifier.height(10.dp))
                TextFieldComponent(
                    email,
                    onValueChange = { updatedEmail -> email = updatedEmail },
                    label = "Email",
                    painterResource = painterResource(id = R.drawable.mail_icon)
                )
                PasswordFieldComponent(
                    password,
                    label = "Password",
                    onValueChange = { updatedPassword -> password = updatedPassword },
                    painterResource(id = R.drawable.lock_icon)
                )
                UnderLinedTextComponent(value = "Forgot your password?", onClick = {
                    navController.navigate(AuthScreen.ForgotPasswordScreen.route)
                })
                Spacer(modifier = Modifier.height(50.dp))
                ButtonComponent(value = "Login", onClick = {
                    viewModel.signIn(email, password)
                })
                Spacer(modifier = Modifier.height(10.dp))
                DividerTextComponent()
                Spacer(modifier = Modifier.height(15.dp))
                ClickableLoginTextComponent(tryToLogin = false, onTextSelected = {
                    onSignUpClick()
                    navController.navigate(AuthScreen.Register.route)
                })
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController(), {}, {})
}