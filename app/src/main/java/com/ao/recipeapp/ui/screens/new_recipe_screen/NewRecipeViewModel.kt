package com.ao.recipeapp.ui.screens.new_recipe_screen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ao.recipeapp.app.model.MediaSelectModel
import java.time.LocalTime

class NewRecipeViewModel : ViewModel() {
    private val _medias = mutableStateListOf<MediaSelectModel>()
    val medias: List<MediaSelectModel>
        get() = _medias

    private val _servesValue = mutableStateOf<Int?>(1)
    val servesValue: Int?
        get() = _servesValue.value

    private val _cookingTimeValue = mutableStateOf<LocalTime?>(null)
    val cookingTimeValue: LocalTime?
        get() = _cookingTimeValue.value

    private val _categoryValue = mutableStateOf<String?>(null)
    val categoryValue: String?
        get() = _categoryValue.value


    fun addMedia(media: MediaSelectModel) {
        _medias.add(media)
    }

    fun removeMedia(media: Int) {
        _medias.removeAt(media)
    }

    fun setServesValue(value: Int) {
        _servesValue.value = value
    }

    fun setCookingTimeValue(value: LocalTime) {
        _cookingTimeValue.value = value
    }

    fun setCategoryValue(value: String) {
        _categoryValue.value = value
    }
}