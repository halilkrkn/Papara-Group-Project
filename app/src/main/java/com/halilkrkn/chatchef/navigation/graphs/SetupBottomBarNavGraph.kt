package com.halilkrkn.chatchef.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.halilkrkn.chatchef.navigation.util.AuthScreen
import com.halilkrkn.chatchef.navigation.util.BottomBarNavigationScreen
import com.halilkrkn.chatchef.navigation.util.Graph
import com.halilkrkn.chatchef.presentation.ChatGptScreen.ChatGptScreen
import com.halilkrkn.chatchef.presentation.FavoriteScreen.FavoriteScreen
import com.halilkrkn.chatchef.presentation.loginScreen.ForgotPasswordScreen
import com.halilkrkn.chatchef.presentation.loginScreen.LoginScreen
import com.halilkrkn.chatchef.presentation.loginScreen.SignUpScreen

@Composable
fun SetupBottomBarNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarNavigationScreen.ChatGptBottomBarNavigation.route,
        route = Graph.BOTTOMBAR,
        modifier = modifier
    ) {

        composable(route = BottomBarNavigationScreen.ChatGptBottomBarNavigation.route) {
            ChatGptScreen(navController = navController)
        }

        composable(route = BottomBarNavigationScreen.FavoriteBottomBarNavigation.route) {
            FavoriteScreen()
        }

        composable(route = AuthScreen.Login.route){
            LoginScreen(navController = navController)
        }

        composable(route = AuthScreen.Register.route){
            SignUpScreen(navController = navController)
        }

        composable(route = AuthScreen.ForgotPasswordScreen.route){
            ForgotPasswordScreen(navController = navController)
        }
    }
}



