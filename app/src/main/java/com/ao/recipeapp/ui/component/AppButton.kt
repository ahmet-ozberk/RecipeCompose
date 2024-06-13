package com.ao.recipeapp.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ao.recipeapp.ui.theme.AppColor
import com.ao.recipeapp.ui.theme.latoFamily

@Composable
fun AppButton(
    onClick: (() -> Unit)?,
    text: String,
    icon: Int? = null,
    height: Dp = 54.dp,
    backgroundColor: Color = AppColor.primary,
    foregroundColor: Color = Color.White,
    elevation: Dp = 2.dp,
    borderColor: Color? = null,
    outlined: Boolean = false,
    modifier: Modifier? = null
){
    if (outlined) {
        OutlinedButton(
            onClick = { onClick?.invoke() },
            modifier = modifier ?: Modifier
                .height(height)
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults
                .outlinedButtonColors(
                contentColor = foregroundColor
            ),
            border = BorderStroke(1.dp, borderColor ?: AppColor.primary),
        ) {
            ButtonContent(text, icon, foregroundColor)
        }
    } else {
        ElevatedButton(
            onClick = { onClick?.invoke() },
            modifier = modifier ?: Modifier
                .height(height)
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor,
                contentColor = foregroundColor
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = elevation)
        ) {
            ButtonContent(text, icon, foregroundColor)
        }
    }
}

@Composable
fun ButtonContent(text: String, icon: Int?, foregroundColor: Color) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        AppText(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
            )
        )
        if (icon != null) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = foregroundColor
            )
        }
    }
}