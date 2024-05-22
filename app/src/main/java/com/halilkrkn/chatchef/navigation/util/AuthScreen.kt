package com.halilkrkn.chatchef.navigation.util

sealed class AuthScreen(
    val route: String
){
    data object ForgotPasswordScreen: AuthScreen("forgot_password")
    data object Login: AuthScreen("login")
    data object Register: AuthScreen("register")

}