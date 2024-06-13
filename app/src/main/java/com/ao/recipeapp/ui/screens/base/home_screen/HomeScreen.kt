package com.ao.recipeapp.ui.screens.base.home_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ao.recipeapp.LocalNavController
import com.ao.recipeapp.R
import com.ao.recipeapp.app.dummy_data.AppDummyData
import com.ao.recipeapp.ui.component.AppText
import com.ao.recipeapp.ui.component.AppTextField
import com.ao.recipeapp.ui.component.KeyboardCloser
import com.ao.recipeapp.ui.component.recipe.CategoryRecipeComponent
import com.ao.recipeapp.ui.component.recipe.PopularCreatorsComponent
import com.ao.recipeapp.ui.component.recipe.RecentRecipe
import com.ao.recipeapp.ui.component.recipe.TrendsRecipeComponent
import com.ao.recipeapp.ui.navigation.Screens
import com.ao.recipeapp.ui.screens.base.base_screen.BaseScreenViewModel
import com.ao.recipeapp.ui.theme.AppColor
import com.ao.recipeapp.ui.theme.latoFamily
import com.ao.recipeapp.utils.AppString

@Composable
fun HomeScreen(navController: NavController, viewModel: BaseScreenViewModel, appNavController: NavHostController) {
    val popularCategoryState = remember { mutableIntStateOf(0) }

    KeyboardCloser { size ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.verticalScroll(
                    state = rememberScrollState(), enabled = true
                )
            ) {
                HomeTitleBar(size, navController, viewModel)
                Spacer(modifier = Modifier.height(20.dp))
                HomeTrendsRecipe(appNavController)
                Spacer(modifier = Modifier.height(24.dp))
                AppText(
                    text = AppString.homePopularCategories,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = AppColor.neutral90,
                    modifier = Modifier.padding(horizontal = 20.dp),
                )
                Spacer(modifier = Modifier.height(12.dp))
                HomePopularCategory(popularCategoryState, appNavController)
                Spacer(modifier = Modifier.height(20.dp))
                HomeRecentRecipe(appNavController)
                Spacer(modifier = Modifier.height(20.dp))
                HomePopularCreators(appNavController)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
private fun HomePopularCreators(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText(
            text = AppString.popularPerson,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = AppColor.neutral90,
        )
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = {}) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AppText(
                    text = AppString.seeAll,
                    color = AppColor.primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.width(7.33.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_next_icon),
                    contentDescription = null,
                    tint = AppColor.primary,
                    modifier = Modifier.size(width = 13.33.dp, height = 11.67.dp)
                )
            }
        }
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(10) {
            PopularCreatorsComponent()
        }
    }
}

@Composable
private fun HomeRecentRecipe(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText(
            text = AppString.recentRecipe,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = AppColor.neutral90,
        )
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = {
            navController.navigate(Screens.RecipeList.route)
        }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AppText(
                    text = AppString.seeAll,
                    color = AppColor.primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.width(7.33.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_next_icon),
                    contentDescription = null,
                    tint = AppColor.primary,
                    modifier = Modifier.size(width = 13.33.dp, height = 11.67.dp)
                )
            }
        }
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(10) {
            RecentRecipe(navController)
        }
    }
}

@Composable
private fun HomePopularCategory(popularCategoryState: MutableIntState, appNavController: NavHostController) {
    ScrollableTabRow(selectedTabIndex = popularCategoryState.intValue,
        edgePadding = 20.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp),
        containerColor = Color.Transparent,
        indicator = { tabPositions ->
            Box(
                Modifier
                    .tabIndicatorOffset(tabPositions[popularCategoryState.intValue])
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(AbsoluteCutCornerShape(corner = CornerSize(12.dp)))
                    .background(AppColor.primary), contentAlignment = Alignment.Center
            ) {
                AppText(
                    text = AppDummyData.categories[popularCategoryState.intValue],
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        },
        contentColor = AppColor.neutral10,
        divider = {}) {
        AppDummyData.categories.forEachIndexed { index, title ->
            Tab(selected = popularCategoryState.intValue == index, onClick = {
                popularCategoryState.intValue = index
            }, text = {
                AppText(
                    text = title,
                    color = AppColor.primary30,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            })
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(10) {
            CategoryRecipeComponent(appNavController)
        }
    }
}

@Composable
private fun HomeTrendsRecipe(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText(
            text = AppString.homeTrends + " \uD83D\uDD25",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = AppColor.neutral90,
        )
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = {
            navController.navigate(Screens.RecipeList.route)
        }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AppText(
                    text = AppString.seeAll,
                    color = AppColor.primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.width(7.33.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_next_icon),
                    contentDescription = null,
                    tint = AppColor.primary,
                    modifier = Modifier.size(width = 13.33.dp, height = 11.67.dp)
                )
            }
        }
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(10) {
            TrendsRecipeComponent(navController)
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun HomeTitleBar(size: Size, navController: NavController, viewModel: BaseScreenViewModel) {
    AppText(
        text = AppString.homeTitle,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .width((size.width * 0.6).dp),
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        color = AppColor.neutral90,
    )
    AppTextField(
        value = mutableStateOf(""),
        onValueChange = {},
        onTap = {
            navController.navigate(Screens.Base.Search.route){
                popUpTo(Screens.Base.Home.route) {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
                viewModel.currentIndex.intValue = 1
            }
        },
        modifier = Modifier.padding(horizontal = 20.dp),
        enabled = false,
        hintText = AppString.searchHint,
        leading = {
            Image(
                painter = painterResource(id = R.drawable.ic_search_icon),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        },
    )
}