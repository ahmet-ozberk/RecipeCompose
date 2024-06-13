package com.ao.recipeapp.ui.screens.base.base_screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ao.recipeapp.ui.component.BottomNavigationBar
import com.ao.recipeapp.ui.navigation.Screens
import com.ao.recipeapp.ui.navigation.bottomToTopEnterTransition
import com.ao.recipeapp.ui.navigation.enterTransition
import com.ao.recipeapp.ui.navigation.exitTransition
import com.ao.recipeapp.ui.navigation.scaleEnterTransition
import com.ao.recipeapp.ui.navigation.scaleExitTransition

import com.ao.recipeapp.ui.navigation.*
import com.ao.recipeapp.ui.navigation.topToBottomExitTransition
import com.ao.recipeapp.ui.screens.base.bookmark_screen.BookmarkScreen
import com.ao.recipeapp.ui.screens.base.home_screen.HomeScreen
import com.ao.recipeapp.ui.screens.base.profile_screen.ProfileScreen
import com.ao.recipeapp.ui.screens.base.recipe_list_screen.RecipeListScreen
import com.ao.recipeapp.ui.screens.base.search_screen.SearchScreen
import com.ao.recipeapp.ui.screens.new_recipe_screen.NewRecipeScreen
import com.ao.recipeapp.ui.screens.recipe_detail_screen.RecipeDetailScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BaseScreen(viewModel: BaseScreenViewModel = viewModel(), appNavController: NavHostController) {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigationBar(navController = navController, appNavController = appNavController)
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            ScreenNavigationConfiguration(navController, viewModel, appNavController)
        }
    }
}

@Composable
fun ScreenNavigationConfiguration(
    navController: NavHostController,
    viewModel: BaseScreenViewModel,
    appNavController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screens.Base.Home.route) {
        composable(route = Screens.Base.Home.route,
            enterTransition = { bottomToTopEnterTransition() },
            exitTransition = { topToBottomExitTransition() }) {
            HomeScreen(navController, viewModel, appNavController)
        }
        composable(route = Screens.Base.Search.route,
            enterTransition = { bottomToTopEnterTransition() },
            exitTransition = { topToBottomExitTransition() }) {
            SearchScreen(appNavController)
        }
        composable(route = Screens.Base.Bookmark.route,
            enterTransition = { bottomToTopEnterTransition() },
            exitTransition = { topToBottomExitTransition() }) {
            BookmarkScreen(appNavController)
        }
        composable(route = Screens.Base.Profile.route,
            enterTransition = { bottomToTopEnterTransition() },
            exitTransition = { topToBottomExitTransition() }) {
            ProfileScreen(navController = navController, appNavController = appNavController)
        }
    }
}