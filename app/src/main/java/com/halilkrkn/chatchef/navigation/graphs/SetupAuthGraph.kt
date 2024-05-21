package com.halilkrkn.chatchef.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.halilkrkn.chatchef.navigation.util.AuthScreen
import com.halilkrkn.chatchef.navigation.util.Graph
import com.halilkrkn.chatchef.presentation.LoginPage.LoginScreen
import com.halilkrkn.chatchef.presentation.LoginPage.SignUpScreen
import com.halilkrkn.chatchef.presentation.MainScreen


@Composable
fun SetupAuthGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = AuthScreen.Login.route,
        modifier = modifier
    ){

        composable(route = AuthScreen.Login.route){
            LoginScreen(navController = navController) {

            }
        }

        composable(route = AuthScreen.Register.route){
            SignUpScreen(navController = navController) {

            }
        }

        composable(route = Graph.BOTTOMBAR){
            MainScreen()
        }
    }
}
