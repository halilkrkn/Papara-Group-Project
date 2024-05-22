package com.halilkrkn.chatchef.navigation.util

import androidx.annotation.DrawableRes
import com.halilkrkn.chatchef.R

sealed class BottomBarNavigationScreen(
    val route: String,
    val title: String,
    @DrawableRes
    val icon: Int,
) {
    data object ChatGptBottomBarNavigation : BottomBarNavigationScreen(
        route = "chat_gpt",
        title = "Chat GPT",
        icon = R.drawable.chat_gpt_icon
    )

    data object FavoriteBottomBarNavigation : BottomBarNavigationScreen(
        route = "favorite",
        title = "Favorite",
        icon = R.drawable.favorite_icon
    )
}