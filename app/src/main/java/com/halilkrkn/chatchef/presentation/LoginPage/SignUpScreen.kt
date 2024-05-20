package com.halilkrkn.chatchef.presentation.LoginPage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.halilkrkn.chatchef.R
import com.halilkrkn.chatchef.navigation.util.AuthScreen
import com.halilkrkn.chatchef.presentation.components.ButtonComponent
import com.halilkrkn.chatchef.presentation.components.ClickableLoginTextComponent
import com.halilkrkn.chatchef.presentation.components.DividerTextComponent
import com.halilkrkn.chatchef.presentation.components.HeadingTextComponent
import com.halilkrkn.chatchef.presentation.components.TextFieldComponent
import com.halilkrkn.chatchef.presentation.components.PasswordFieldComponent

@Composable
fun SignUpScreen(navController:NavController,onLoginClick: () -> Unit = {}, onSignUpClick: () -> Unit = {}) {

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .padding(28.dp)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {

            Spacer(modifier = Modifier.height(20.dp))
            HeadingTextComponent("Create an Account")
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldComponent("Name", painterResource(id = R.drawable.profile_icon))
            TextFieldComponent("Surname", painterResource(id = R.drawable.profile_icon))
            TextFieldComponent("Email", painterResource(id = R.drawable.mail_icon))
            PasswordFieldComponent("Password", painterResource(id = R.drawable.lock_icon))
            Spacer(modifier = Modifier.height(120.dp))
            ButtonComponent(value = "Register", onClick = {
                onSignUpClick()
                navController.navigate(AuthScreen.Login.route)
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SignUpScreenPreview() {
    //SignUpScreen({},{})
}

