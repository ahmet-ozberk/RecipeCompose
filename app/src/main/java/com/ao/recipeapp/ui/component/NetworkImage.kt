package com.ao.recipeapp.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.ao.recipeapp.app.dummy_data.AppDummyData
import com.ao.recipeapp.ui.theme.AppColor
import kotlin.random.Random

enum class DummyImageType {
    RECIPE,
    USER
}

@Composable
fun NetworkImage(
    imageUrl: String? = null,
    isRandomDummyImage: Boolean = false,
    dummyImageType: DummyImageType = DummyImageType.RECIPE
) {
    val randomValue = Random.nextInt(0, 100)
    SubcomposeAsyncImage(
        model = if (isRandomDummyImage) if (dummyImageType == DummyImageType.RECIPE) AppDummyData.getRandomRecipeImage(
            randomValue
        ) else AppDummyData.getRandomProfileImage(randomValue) else imageUrl
            ?: "",
        contentDescription = null,
        contentScale = ContentScale.Crop,
        loading = {
            Box(
                modifier = Modifier.fillMaxSize().padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = AppColor.primary,
                    strokeWidth = 2.dp
                )
            }
        },
        error = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null)
            }
        }
    )
}