package com.halilkrkn.chatchef.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.halilkrkn.chatchef.navigation.util.BottomBarNavigationScreen
import com.halilkrkn.chatchef.presentation.ChatGptScreen.ChatScreen
import com.halilkrkn.chatchef.presentation.FavoriteScreen.FavoriteScreen

@Composable
fun SetupBottomBarNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarNavigationScreen.ChatGptBottomBarNavigation.route
    ) {
        composable(route = BottomBarNavigationScreen.ChatGptBottomBarNavigation.route){
            ChatScreen()
        }

        composable(route = BottomBarNavigationScreen.FavoriteBottomBarNavigation.route){
            FavoriteScreen()
        }
    }
}