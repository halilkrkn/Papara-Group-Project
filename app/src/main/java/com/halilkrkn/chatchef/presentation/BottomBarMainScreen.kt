package com.halilkrkn.chatchef.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.halilkrkn.chatchef.navigation.graphs.SetupBottomBarNavGraph
import com.halilkrkn.chatchef.navigation.util.BottomBarNavigationScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        SetupBottomBarNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarNavigationScreen.ChatGptBottomBarNavigation,
        BottomBarNavigationScreen.FavoriteBottomBarNavigation,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Black,
        modifier = Modifier
                .height(IntrinsicSize.Min)
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomBarNavigationScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    NavigationBarItem(
        modifier = Modifier
            .requiredHeight(96.dp),
        label = {
            Text(
                text = screen.title
            )
        },

        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "Navigation Icon"
            )
        },

        selected = currentDestination?.hierarchy?.any { navDestination ->
            navDestination.route == screen.route
        } == true,

        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
//        alwaysShowLabel = false
    )
}