package com.ao.recipeapp.utils.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
fun LocalTime.formattedTime(): String {
    var resultText = ""

    resultText += if (hour == 0) {
        ""
    } else if (hour < 10) {
        "$hour saat"
    } else {
        "$hour saat"
    }

    resultText += if (minute == 0) {
        ""
    } else if (minute < 10) {
        " $minute dakika"
    } else {
        " $minute dakika"
    }

    return resultText.trim()
}