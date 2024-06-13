package com.ao.recipeapp.ui.screens.base.bookmark_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ao.recipeapp.ui.component.AppText
import com.ao.recipeapp.ui.component.recipe.RecipeComponent
import com.ao.recipeapp.ui.component.scrollbar
import com.ao.recipeapp.ui.screens.base.base_screen.BaseScreen
import com.ao.recipeapp.ui.theme.AppColor
import com.ao.recipeapp.ui.theme.latoFamily
import com.ao.recipeapp.utils.AppString

@Composable
fun BookmarkScreen(navController: NavController) {
    val listState = rememberLazyListState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            AppText(
                text = AppString.bookmarkRecipe,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = AppColor.neutral90,
                ),
                modifier = Modifier.padding(start = 20.dp, bottom = 12.dp, top = 20.dp,)
            )
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.scrollbar(state = listState, horizontal = false)
            ) {
                items(10) {
                    Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                        RecipeComponent(navController = navController)
                    }
                    Divider(
                        modifier = Modifier.padding(top = 20.dp), color = AppColor.neutral10
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewBookmarkScreen() {
    val navController = rememberNavController()
    BaseScreen(appNavController = navController)
}