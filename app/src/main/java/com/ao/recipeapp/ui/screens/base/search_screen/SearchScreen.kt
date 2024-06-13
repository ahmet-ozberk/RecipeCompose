package com.ao.recipeapp.ui.screens.base.search_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ao.recipeapp.R
import com.ao.recipeapp.app.dummy_data.AppDummyData
import com.ao.recipeapp.ui.component.AppText
import com.ao.recipeapp.ui.component.AppTextField
import com.ao.recipeapp.ui.component.KeyboardCloser
import com.ao.recipeapp.ui.component.recipe.RecipeComponent
import com.ao.recipeapp.ui.navigation.Screens
import com.ao.recipeapp.ui.screens.base.base_screen.BaseScreen
import com.ao.recipeapp.ui.theme.AppColor
import com.ao.recipeapp.ui.theme.latoFamily
import com.ao.recipeapp.utils.AppString

@Composable
fun SearchScreen(navController: NavController) {
    val searchValue = remember { mutableStateOf("") }
    KeyboardCloser {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                AppTextField(value = searchValue,
                    onValueChange = { searchValue.value = it },
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(vertical = 12.dp),
                    hintText = AppString.searchHint,
                    leading = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_search_icon),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    trailing = {
                        if (searchValue.value.trim().isNotEmpty()) {
                            IconButton(onClick = { searchValue.value = "" }) {
                                Image(
                                    painter = painterResource(id = R.drawable.im_add_icon),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(16.dp)
                                        .rotate(45f)
                                )
                            }
                        }
                    })
                if (searchValue.value.trim().isEmpty()) {
                    UnsearchChips {
                        searchValue.value = it
                    }
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(24.dp)) {
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
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun UnsearchChips(onClick: (value: String) -> Unit) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(
            12.dp, alignment = Alignment.CenterHorizontally
        ), verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(20.dp)
    ) {
        AppDummyData.searchCategories.forEach {
            Box(modifier = Modifier
                .clip(
                    RoundedCornerShape(24.dp)
                )
                .clickable {
                    onClick(it)
                }
                .background(AppColor.primary)
                .padding(horizontal = 12.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center) {
                AppText(
                    text = it, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium
                )
            }
        }
    }
}