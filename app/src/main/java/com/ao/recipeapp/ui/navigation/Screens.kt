package com.ao.recipeapp.ui.navigation


sealed class Screens(val route: String) {
    data object Splash : Screens("splash")

    data object Auth : Screens("auth") {
        data object Login : Screens("login")
        data object Register : Screens("register")
    }

    data object Base : Screens("base") {
        data object Home : Screens("home")
        data object Search : Screens("search")
        data object Bookmark : Screens("bookmark")
        data object Profile : Screens("profile")
    }

    data object RecipeList : Screens("recipe_list")
    data object NewRecipe : Screens("new_recipe")
    data object RecipeDetail : Screens("recipe_detail")
    data object Camera : Screens("camera")
}
