package com.halilkrkn.chatchef.presentation.loginScreen

import AuthViewModel
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.halilkrkn.chatchef.R
import com.halilkrkn.chatchef.navigation.util.AuthScreen
import com.halilkrkn.chatchef.presentation.components.ButtonComponent
import com.halilkrkn.chatchef.presentation.components.ClickableLoginTextComponent
import com.halilkrkn.chatchef.presentation.components.DividerTextComponent
import com.halilkrkn.chatchef.presentation.components.HeadingTextComponent
import com.halilkrkn.chatchef.presentation.components.TextFieldComponent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.halilkrkn.chatchef.presentation.components.Loader
import com.halilkrkn.chatchef.presentation.components.PasswordFieldComponent

@Composable
fun SignUpScreen(
    navController: NavController,
    onLoginClick: () -> Unit = {},
    viewModel: AuthViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    val context = LocalContext.current


    LaunchedEffect(uiState.error) {
        uiState.error?.let { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }


    LaunchedEffect(uiState.user) {
        uiState.user?.let {
            navController.navigate(AuthScreen.Login.route) {
                popUpTo(AuthScreen.Login.route) {
                    inclusive = true }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        if (uiState.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,

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

                Spacer(modifier = Modifier.height(20.dp))
                HeadingTextComponent("Create an Account")
                Spacer(modifier = Modifier.height(20.dp))

                TextFieldComponent(
                    stateValue = firstName,
                    onValueChange = { updatedFirstName ->
                        firstName = updatedFirstName
                    },
                    label = "First Name",
                    painterResource = painterResource(id = R.drawable.profile_icon)
                )

                TextFieldComponent(
                    stateValue = lastName,
                    onValueChange = { updatedLastName ->
                        lastName = updatedLastName
                    },
                    label = "Last Name",
                    painterResource = painterResource(id = R.drawable.profile_icon)
                )

                TextFieldComponent(
                    stateValue = email,
                    onValueChange = { updatedEmail ->
                        email = updatedEmail
                    },
                    label = "Email",
                    painterResource = painterResource(id = R.drawable.mail_icon)
                )

                PasswordFieldComponent(
                    stateValue = password,
                    onValueChange = { updatedPassword ->
                        password = updatedPassword
                    },
                    label = "Password",
                    painterResource = painterResource(id = R.drawable.lock_icon)
                )

                Spacer(modifier = Modifier.height(70.dp))
                ButtonComponent(value = "Register", onClick = {
                    viewModel.signUp(email, password, firstName, lastName)
                })
                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryToLogin = true, onTextSelected = {
                    onLoginClick()
                    navController.navigate(AuthScreen.Login.route)
                })
            }
        }
    }
}
