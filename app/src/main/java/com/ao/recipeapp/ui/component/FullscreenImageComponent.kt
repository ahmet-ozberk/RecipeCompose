package com.ao.recipeapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ao.recipeapp.R

@Composable
fun FullscreenImageComponent(imageUrl: String? = null, setShowDialog: (Boolean) -> Unit) {
    Dialog(
        onDismissRequest = { setShowDialog(false) },
        DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            imageUrl?.let {
                NetworkImage(imageUrl = it, contentScale = ContentScale.None)
            } ?: run {
                NetworkImage(isRandomDummyImage = true, contentScale = ContentScale.None)
            }
            IconButton(
                onClick = { setShowDialog(false) },
                modifier = Modifier
                    .offset((-8).dp, 8.dp)
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.im_add_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                        .rotate(45f),
                    tint = Color.White
                )
            }
        }
    }
}