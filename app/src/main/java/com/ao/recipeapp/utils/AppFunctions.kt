package com.ao.recipeapp.utils

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.provider.OpenableColumns
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

@Composable
fun screenWidth(combine: Double = 1.0): Float {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp.toFloat() * combine.toFloat()
}

@Composable
fun screenHeight(combine: Double = 1.0): Float {
    val configuration = LocalConfiguration.current
    return configuration.screenHeightDp.toFloat() * combine.toFloat()
}

fun convertBitmapToFile(context: Context,bitmap: Bitmap): File {
    val file = File(context.cacheDir, "temp.jpg")
    ByteArrayOutputStream().use { stream ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        file.writeBytes(stream.toByteArray())
    }
    return file
}

fun getRealPathFromURI(context: Context, uri: Uri): String? {
    val contentResolver: ContentResolver = context.contentResolver
    val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
    cursor?.use {
        val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        it.moveToFirst()
        val name = it.getString(nameIndex)
        val file = File(context.cacheDir, name)
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)
        inputStream?.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
        return file.absolutePath
    }
    return null
}