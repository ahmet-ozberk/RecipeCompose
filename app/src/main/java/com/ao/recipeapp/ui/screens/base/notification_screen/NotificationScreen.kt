package com.ao.recipeapp.ui.screens.base.notification_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ao.recipeapp.ui.component.AppText
import com.ao.recipeapp.ui.theme.latoFamily

@Composable
fun NotificationScreen(navController: NavController){
    Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AppText(text = "Notification Screen", modifier = Modifier.align(Alignment.Center))
    }
}