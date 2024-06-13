package com.ao.recipeapp.ui.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ao.recipeapp.LocalNavController
import com.ao.recipeapp.R

import com.ao.recipeapp.app.model.BottomNavItem
import com.ao.recipeapp.ui.navigation.Screens
import com.ao.recipeapp.ui.screens.base.base_screen.BaseScreen
import com.ao.recipeapp.ui.screens.base.base_screen.BaseScreenViewModel
import com.ao.recipeapp.ui.theme.AppColor

@Composable
fun BottomNavigationBar(
    navController: NavController,
    viewModel: BaseScreenViewModel = viewModel(),
    appNavController: NavHostController
) {
    val currentIndex = viewModel.currentIndex

    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 20.dp, shape = RectangleShape)
                .background(Color.White)
                .padding(bottom = 16.dp, start = 8.dp, end = 8.dp, top = 12.dp),
        ) {
            Row {
                BottomNavItem.Tab.items.forEachIndexed { index, item ->
                    IconButton(onClick = {
                        if(item != BottomNavItem.Tab.NewRecipe){
                            navController.navigate(item.route.route) {
                                popUpTo(Screens.Base.Home.route) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            currentIndex.intValue = index
                        }
                    }, modifier = Modifier.weight(1f)) {
                        val isActive = currentIndex.intValue == index
                        if (item == BottomNavItem.Tab.NewRecipe) {
                            Spacer(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(48.dp)
                            )
                        } else {
                            Image(
                                painterResource(id = if (isActive) item.activeIcon else item.inactiveIcon),
                                contentDescription = item.route.route,
                                colorFilter = if (index == 1 && isActive) ColorFilter.tint(color = AppColor.primary) else null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }

        }
        Box(modifier = Modifier
            .offset(y = (-24).dp)
            .clip(CircleShape)
            .size(48.dp)
            .clickable { appNavController.navigate(Screens.NewRecipe.route) }
            .background(AppColor.primary)
            .align(Alignment.Center), contentAlignment = Alignment.Center) {
            Image(
                painterResource(id = R.drawable.ic_new_food),
                contentDescription = BottomNavItem.Tab.NewRecipe.route.route,
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    val navController = rememberNavController()
    BaseScreen(appNavController = navController)
}