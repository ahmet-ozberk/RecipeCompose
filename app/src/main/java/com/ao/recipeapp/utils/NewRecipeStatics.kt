package com.ao.recipeapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime

object NewRecipeStatics {
    @RequiresApi(Build.VERSION_CODES.O)
    val times = listOf(
        LocalTime.of(0, 2),
        LocalTime.of(0, 5),
        LocalTime.of(0, 10),
        LocalTime.of(0, 15),
        LocalTime.of(0, 20),
        LocalTime.of(0, 25),
        LocalTime.of(0, 30),
        LocalTime.of(0, 35),
        LocalTime.of(0, 40),
        LocalTime.of(0, 45),
        LocalTime.of(0, 50),
        LocalTime.of(0, 55),
        LocalTime.of(1, 0),
        LocalTime.of(1, 15),
        LocalTime.of(1, 30),
        LocalTime.of(1, 45),
        LocalTime.of(2, 0),
        LocalTime.of(2, 30),
        LocalTime.of(3, 0),
        LocalTime.of(3, 30),
        LocalTime.of(4, 0),
        LocalTime.of(4, 30),
        LocalTime.of(5, 0),
        LocalTime.of(5, 30),
        LocalTime.of(6, 0)
    )

    val serves = listOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
        15, 20, 25, 30, 35, 40, 45, 50,
        80, 100, 125, 150, 200, 250, 300,
        350, 400, 450, 500
    )

}