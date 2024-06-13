package com.ao.recipeapp.ui.component.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.ao.recipeapp.R
import com.ao.recipeapp.app.dummy_data.AppDummyData
import com.ao.recipeapp.app.dummy_data.lorem
import com.ao.recipeapp.ui.component.AppText
import com.ao.recipeapp.ui.component.DummyImageType
import com.ao.recipeapp.ui.component.NetworkImage
import com.ao.recipeapp.ui.navigation.Screens
import com.ao.recipeapp.ui.screens.base.base_screen.BaseScreen
import com.ao.recipeapp.ui.theme.AppColor
import com.ao.recipeapp.ui.theme.latoFamily
import com.ao.recipeapp.utils.extensions.customClickable
import kotlin.random.Random

@Composable
fun TrendsRecipeComponent(navController: NavController) {
    val randomValue = Random.nextInt(0, 100)
    var saveState by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.customClickable {
        navController.navigate(Screens.RecipeDetail.route)
    }.width(280.dp)) {
        Box(
            modifier = Modifier
                .size(width = 280.dp, height = 180.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    AppColor.neutral10
                )
        ) {
            NetworkImage(isRandomDummyImage = true)
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .height(30.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(AppColor.neutral30.copy(alpha = 0.8f))
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_product_start),
                                contentDescription = null,
                                modifier = Modifier.size(12.dp),
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            AppText(
                                text = "4.5",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                    Box(modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .clickable {
                            saveState = !saveState
                        }
                        .background(Color.White)
                        .padding(6.dp), contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_bookmark_passive),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = if (!saveState) AppColor.neutral90 else AppColor.primary)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(AppColor.neutral90.copy(alpha = 0.6f))
                        .padding(6.dp), contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_player_play),
                        contentDescription = null,
                    )
                }
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(AppColor.neutral30.copy(alpha = 0.8f))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                        .align(Alignment.End)
                ) {
                    AppText(
                        text = "15:10",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            AppText(
                text = 100.lorem(),
                color = AppColor.neutral90,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.MoreVert, contentDescription = null)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(AppColor.neutral10),
                contentAlignment = Alignment.Center,
            ) {
                NetworkImage(isRandomDummyImage = true, dummyImageType = DummyImageType.USER)
            }
            Spacer(
                modifier = Modifier.width(8.dp)
            )
            AppText(
                text = 14.lorem(),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = AppColor.neutral40,
            )
        }
    }
}