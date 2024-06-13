package com.ao.recipeapp.ui.component.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ao.recipeapp.ui.component.AppText
import com.ao.recipeapp.ui.component.DummyImageType
import com.ao.recipeapp.ui.component.NetworkImage
import com.ao.recipeapp.ui.screens.base.base_screen.BaseScreen
import com.ao.recipeapp.ui.theme.AppColor
import com.ao.recipeapp.ui.theme.latoFamily

@Composable
fun PopularCreatorsComponent() {
    Box(modifier = Modifier.width(75.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .background(AppColor.neutral10)
            ) {
                NetworkImage(isRandomDummyImage = true, dummyImageType = DummyImageType.USER)
            }
            Spacer(modifier = Modifier.height(8.dp))
            AppText(
                text = "James Wolden",
                color = AppColor.neutral90,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )
        }
    }
}