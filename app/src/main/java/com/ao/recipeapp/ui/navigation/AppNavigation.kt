package com.ao.recipeapp.ui.navigation

import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.ao.recipeapp.ui.screens.new_recipe_screen.NewRecipeScreen
import com.ao.recipeapp.ui.screens.recipe_detail_screen.RecipeDetailScreen
import com.ao.recipeapp.ui.screens.splash_screen.SplashScreen
import com.ao.recipeapp.ui.screens.auth.login_screen.LoginScreen
import com.ao.recipeapp.ui.screens.auth.register_screen.RegisterScreen
import com.ao.recipeapp.ui.screens.base.base_screen.BaseScreen
import com.ao.recipeapp.ui.screens.base.recipe_list_screen.RecipeListScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Screens.Splash.route
    ) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navController)
        }
        authGraph(navController)
        composable(route = Screens.Base.route,
            enterTransition = { bottomToTopEnterTransition() },
            exitTransition = { topToBottomExitTransition() }) {
            BaseScreen(appNavController = navController)
        }
        composable(
            route = Screens.RecipeList.route,
            enterTransition = { bottomToTopEnterTransition() },
            exitTransition = { topToBottomExitTransition() }) {
            RecipeListScreen(navController)
        }
        composable(route = Screens.NewRecipe.route,
            enterTransition = { bottomToTopEnterTransition() },
            exitTransition = { topToBottomExitTransition() }) {
            NewRecipeScreen(navController)
        }
        composable(route = Screens.RecipeDetail.route,
            enterTransition = { bottomToTopEnterTransition() },
            exitTransition = { topToBottomExitTransition() }) {
            RecipeDetailScreen(navController)
        }
    }
}


fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(startDestination = Screens.Auth.Login.route, route = Screens.Auth.route) {
        composable(route = Screens.Auth.Login.route) {
            LoginScreen(navController)
        }
        composable(route = Screens.Auth.Register.route) {
            RegisterScreen(navController)
        }
    }
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition() = slideIntoContainer(
    AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(300)
)

fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition() = slideOutOfContainer(
    AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(300)
) + fadeOut(animationSpec = tween(200))

fun scaleEnterTransition(): EnterTransition = scaleIn(animationSpec = tween(300))

fun scaleExitTransition(): ExitTransition =
    scaleOut(animationSpec = tween(300)) + fadeOut(animationSpec = tween(200))

fun slideUpToDownEnterTransition() = slideInVertically(
    initialOffsetY = { it },
    animationSpec = tween(150)
)

fun slideDownToUpExitTransition() = slideOutVertically(
    targetOffsetY = { it },
    animationSpec = tween(150)
) + fadeOut(animationSpec = tween(100))

fun AnimatedContentTransitionScope<NavBackStackEntry>.bottomToTopEnterTransition() =
    slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(300)
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.topToBottomExitTransition() =
    slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(300)
    ) + fadeOut(animationSpec = tween(200))