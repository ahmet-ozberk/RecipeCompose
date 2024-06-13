package com.ao.recipeapp.ui.component.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ao.recipeapp.R
import com.ao.recipeapp.app.dummy_data.lorem
import com.ao.recipeapp.ui.component.AppText
import com.ao.recipeapp.ui.component.NetworkImage
import com.ao.recipeapp.ui.navigation.Screens
import com.ao.recipeapp.ui.screens.base.base_screen.BaseScreen
import com.ao.recipeapp.ui.theme.AppColor
import com.ao.recipeapp.ui.theme.latoFamily
import com.ao.recipeapp.utils.AppString
import com.ao.recipeapp.utils.extensions.customClickable

@Composable
fun CategoryRecipeComponent(navController: NavController) {
    Box(
        modifier = Modifier
            .customClickable {
                navController.navigate(Screens.RecipeDetail.route)
            }.size(width = 150.dp, height = 231.dp)
    ) {
        Box(
            modifier = Modifier
                .size(width = 150.dp, height = 176.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(AppColor.neutral10)
                .align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier.padding(
                    top = 66.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 12.dp
                )
            ) {
                AppText(
                    text = 21.lorem(),
                    fontSize = 14.sp,
                    color = AppColor.neutral90,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.weight(1f))
                AppText(
                    text = AppString.time,
                    fontSize = 12.sp,
                    color = AppColor.neutral30,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppText(
                        text = "10 dk",
                        fontSize = 12.sp,
                        color = AppColor.neutral90,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Box(modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .clickable {
                        }
                        .background(Color.White)
                        .padding(6.dp), contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_bookmark_passive),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = AppColor.neutral90)
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .size(110.dp)
                .shadow(
                    elevation = 14.dp,
                    shape = CircleShape
                )
                .background(Color.White, shape = CircleShape)
                .align(Alignment.TopCenter)
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .shadow(
                        elevation = 14.dp,
                        shape = CircleShape
                    )
                    .background(Color.White, shape = CircleShape)
                    .align(Alignment.Center)
            ) {
                NetworkImage(isRandomDummyImage = true)
            }
        }
    }
}