package com.halilkrkn.chatchef.navigation.util

sealed class AuthScreen(
    val route: String
){

    data object Login: AuthScreen("login")
    data object Register: AuthScreen("register")

}