package com.ao.recipeapp.ui.component.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.ao.recipeapp.app.dummy_data.lorem
import com.ao.recipeapp.ui.component.AppText
import com.ao.recipeapp.ui.component.DummyImageType
import com.ao.recipeapp.ui.component.NetworkImage
import com.ao.recipeapp.ui.navigation.Screens
import com.ao.recipeapp.ui.screens.base.base_screen.BaseScreen
import com.ao.recipeapp.ui.theme.AppColor
import com.ao.recipeapp.ui.theme.latoFamily
import com.ao.recipeapp.utils.AppString
import com.ao.recipeapp.utils.extensions.customClickable

@Composable
fun RecipeComponent(isEdit: Boolean = false, navController: NavController) {
    val isMenuExpanded = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .customClickable {
                navController.navigate(Screens.RecipeDetail.route)
            }
        .fillMaxWidth()
        .background(Color.White)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(42.dp)
                    .background(AppColor.neutral10)
            ) {
                NetworkImage(isRandomDummyImage = true, dummyImageType = DummyImageType.USER)
            }
            Spacer(modifier = Modifier.size(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                AppText(
                    text = "Şef Ayşe Hanım",
                    fontSize = 16.sp,
                    color = AppColor.neutral90,
                    fontWeight = FontWeight.Normal,
                )
                AppText(
                    text = "@shefaysehanim",
                    fontSize = 12.sp,
                    color = AppColor.neutral90,
                    fontWeight = FontWeight.Medium,
                )
            }
            AppText(text = "03/04/2024", fontSize = 12.sp, color = AppColor.neutral40)
        }
        Column(modifier = Modifier.padding(start = 50.dp)) {
            AppText(
                text = 56.lorem(),
                fontSize = 16.sp,
                color = AppColor.neutral90,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                modifier = Modifier.padding(bottom = 4.dp),
            )
            Spacer(modifier = Modifier.size(4.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(AppColor.neutral10)
                    .height(180.dp)
            ) {
                NetworkImage(isRandomDummyImage = true)
                if (isEdit) {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(CircleShape)
                            .clickable { isMenuExpanded.value = true }
                            .size(32.dp)
                            .background(Color.White)
                            .align(Alignment.TopEnd),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Sharp.MoreVert,
                            contentDescription = null,
                            modifier = Modifier.rotate(90f),
                            tint = AppColor.primary
                        )
                        DropdownMenu(
                            expanded = isMenuExpanded.value,
                            onDismissRequest = { isMenuExpanded.value = false }) {
                            DropdownMenuItem(
                                text = { Text(text = AppString.edit) },
                                onClick = { isMenuExpanded.value = false })
                            DropdownMenuItem(
                                text = { Text(text = AppString.delete) },
                                onClick = { isMenuExpanded.value = false })
                        }
                    }

                }
            }

        }
    }
}