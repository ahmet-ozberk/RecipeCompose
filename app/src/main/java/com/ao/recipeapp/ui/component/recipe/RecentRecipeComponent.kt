package com.ao.recipeapp.ui.component.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ao.recipeapp.app.dummy_data.lorem
import com.ao.recipeapp.ui.component.AppText
import com.ao.recipeapp.ui.component.NetworkImage
import com.ao.recipeapp.ui.navigation.Screens
import com.ao.recipeapp.ui.screens.base.base_screen.BaseScreen
import com.ao.recipeapp.ui.theme.AppColor
import com.ao.recipeapp.ui.theme.latoFamily
import com.ao.recipeapp.utils.extensions.customClickable

@Composable
fun RecentRecipe(navController: NavController) {
    Box(modifier = Modifier
        .customClickable {
            navController.navigate(Screens.RecipeDetail.route)
        }
        .width(124.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .aspectRatio(1f)
                    .background(AppColor.neutral10)
            ) {
                NetworkImage(isRandomDummyImage = true)
            }
            AppText(
                text = 28.lorem(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 2.dp, bottom = 4.dp),
            )
            AppText(
                text = "By James Wolden",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 10.sp,
                color = AppColor.neutral40,
            )
        }
    }
}