package com.ao.recipeapp.app.model

import androidx.annotation.DrawableRes
import com.ao.recipeapp.R
import com.ao.recipeapp.ui.navigation.Screens

sealed class BottomNavItem(
    val route: Screens,
    @DrawableRes val activeIcon: Int,
    @DrawableRes val inactiveIcon: Int
) {
    data class Tab(
        val bottomNav: BottomNavItem
    ) {
        object Home : BottomNavItem(
            Screens.Base.Home,
            R.drawable.ic_home_active,
            R.drawable.ic_home_passive
        )

        object Search : BottomNavItem(
            Screens.Base.Search,
            R.drawable.ic_search_icon,
            R.drawable.ic_search_icon
        )

        object NewRecipe : BottomNavItem(
            Screens.NewRecipe,
            R.drawable.ic_new_food,
            R.drawable.ic_new_food
        )

        object Bookmark : BottomNavItem(
            Screens.Base.Bookmark,
            R.drawable.ic_bookmark_active,
            R.drawable.ic_bookmark_passive
        )

        object Profile : BottomNavItem(
            Screens.Base.Profile,
            R.drawable.ic_profile_active,
            R.drawable.ic_profile_passive
        )

        companion object {
            val items = listOf(Home, Search, NewRecipe, Bookmark, Profile)
        }
    }
}
