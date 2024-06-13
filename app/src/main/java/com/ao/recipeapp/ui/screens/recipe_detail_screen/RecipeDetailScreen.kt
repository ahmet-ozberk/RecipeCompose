package com.ao.recipeapp.ui.screens.recipe_detail_screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ao.recipeapp.R
import com.ao.recipeapp.app.dummy_data.lorem
import com.ao.recipeapp.ui.component.AppText
import com.ao.recipeapp.ui.component.NetworkImage
import com.ao.recipeapp.ui.component.VideoPlayer
import com.ao.recipeapp.ui.theme.AppColor
import com.ao.recipeapp.utils.AppString

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "NewApi")
@Composable
fun RecipeDetailScreen(navController: NavController) {
    val isSaved = remember {
        mutableStateOf(false)
    }
    val pagerState = rememberPagerState(pageCount = { 4 })
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
                    AppString.recipeDetailTitle,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Medium,
                    ),
                    modifier = Modifier.background(Color.White)
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { isSaved.value = !isSaved.value }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark_passive),
                        contentDescription = null,
                        tint = if (isSaved.value) AppColor.primary else AppColor.neutral40
                    )
                }
            }
            AppText(
                text = 80.lorem(),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppColor.neutral90
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .height(200.dp)
                    .background(AppColor.neutral10)
            ) {
                HorizontalPager(state = pagerState) { pagerCount ->
                    if (pagerCount % 2 == 0) {
                        //VideoPlayer(isDummyVideo = true)
                    } else {
                        NetworkImage(isRandomDummyImage = true)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RecipeDetailScreenPreview() {
    val navController = rememberNavController()
    RecipeDetailScreen(navController = navController)
}