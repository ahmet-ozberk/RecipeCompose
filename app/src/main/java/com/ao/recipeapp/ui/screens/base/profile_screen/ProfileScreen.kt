package com.ao.recipeapp.ui.screens.base.profile_screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ao.recipeapp.app.dummy_data.lorem
import com.ao.recipeapp.ui.component.AppButton
import com.ao.recipeapp.ui.component.AppText
import com.ao.recipeapp.ui.component.DummyImageType
import com.ao.recipeapp.ui.component.NetworkImage
import com.ao.recipeapp.ui.component.recipe.RecipeComponent
import com.ao.recipeapp.ui.navigation.Screens
import com.ao.recipeapp.ui.screens.base.base_screen.BaseScreen
import com.ao.recipeapp.ui.theme.AppColor
import com.ao.recipeapp.ui.theme.latoFamily
import com.ao.recipeapp.utils.AppString

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController, appNavController: NavHostController) {
    val profileMenuAction = remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
            ProfileTitle(profileMenuAction, appNavController, navController)
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(100.dp)
                        .background(AppColor.neutral10)
                ) {
                    NetworkImage(isRandomDummyImage = true, dummyImageType = DummyImageType.USER)
                }
                AppButton(
                    onClick = { /*TODO*/ },
                    text = AppString.edit,
                    outlined = true,
                    foregroundColor = AppColor.primary,
                    height = 36.dp,
                    modifier = Modifier
                )
            }
            AppText(
                text = "Alessandra Blair",
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppColor.neutral90
            )
            AppText(
                text = 100.lorem(),
                fontSize = 14.sp,
                color = AppColor.neutral40,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(horizontal = 20.dp),
                lineHeight = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                InfoBox(title = "Tarifler", value = "18")
                InfoBox(title = "Takipçiler", value = "14K")
                InfoBox(title = "Takip Edilenler", value = "120")
            }
            Divider(modifier = Modifier
                .padding(top = 20.dp, bottom = 12.dp)
                .height(1.dp)
                .background(AppColor.neutral20))
            for (i in 1..18) {
                Box (modifier = Modifier.padding(20.dp)){
                    RecipeComponent(isEdit = true,appNavController)
                }
            }
        }
    }
}

@Composable
private fun InfoBox(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AppText(text = title, fontSize = 12.sp, color = AppColor.neutral40)
        AppText(
            text = value,
            fontSize = 20.sp,
            color = AppColor.neutral90,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun ProfileTitle(
    profileMenuAction: MutableState<Boolean>,
    appNavController: NavHostController,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .padding(22.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AppText(
            text = AppString.myProfile,
            fontSize = 24.sp,
            color = AppColor.neutral90,
            fontWeight = FontWeight.SemiBold
        )
        Box {
            IconButton(onClick = { profileMenuAction.value = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    modifier = Modifier.rotate(90f)
                )
            }
            DropdownMenu(expanded = profileMenuAction.value,
                onDismissRequest = { profileMenuAction.value = false }) {
                DropdownMenuItem(text = { AppText(text = AppString.exitApp) }, onClick = {
                    profileMenuAction.value = false
                    appNavController.navigate(Screens.Auth.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                })
                DropdownMenuItem(text = {
                    AppText(
                        text = AppString.deleteAccount,
                        color = Color.Red
                    )
                }, onClick = {
                    profileMenuAction.value = false
                    Toast.makeText(
                        navController.context,
                        "Bu işlem şuan için desteklenmemektedir.",
                        Toast.LENGTH_SHORT
                    ).show()
                })
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    BaseScreen(appNavController = navController)
}