package com.ao.recipeapp.ui.screens.base.recipe_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ao.recipeapp.R
import com.ao.recipeapp.ui.component.AppText
import com.ao.recipeapp.ui.component.recipe.RecipeComponent
import com.ao.recipeapp.ui.component.scrollbar
import com.ao.recipeapp.ui.theme.AppColor
import com.ao.recipeapp.ui.theme.latoFamily
import com.ao.recipeapp.utils.AppString

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecipeListScreen(navController: NavController) {
    val listState = rememberLazyListState()
    Scaffold(modifier = Modifier.background(Color.White)) {
        Column(modifier = Modifier.padding(it)) {
            Row(
                modifier = Modifier.padding(start = 12.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_back_button),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                AppText(
                    AppString.recipes,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Medium,
                    ),
                    modifier = Modifier.background(Color.White)
                )
            }
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
fun RecipeListScreenPreview() {
    val navController = rememberNavController()
    RecipeListScreen(navController)
}