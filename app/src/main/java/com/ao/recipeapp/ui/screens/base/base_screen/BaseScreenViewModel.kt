package com.ao.recipeapp.ui.screens.base.base_screen

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BaseScreenViewModel : ViewModel() {
    val currentIndex = mutableIntStateOf(0)
}