package com.ao.recipeapp.ui.screens.new_recipe_screen

import android.Manifest
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ao.recipeapp.R
import com.ao.recipeapp.app.dummy_data.AppDummyData
import com.ao.recipeapp.app.model.MediaSelectModel
import com.ao.recipeapp.app.model.MediaType
import com.ao.recipeapp.ui.component.AppButton
import com.ao.recipeapp.ui.component.AppPopupMenu
import com.ao.recipeapp.ui.component.AppText
import com.ao.recipeapp.ui.component.AppTextField
import com.ao.recipeapp.ui.component.KeyboardCloser
import com.ao.recipeapp.ui.component.PopupMenuItem
import com.ao.recipeapp.ui.component.AppVideoPlayer
import com.ao.recipeapp.ui.theme.AppColor
import com.ao.recipeapp.utils.AppString
import com.ao.recipeapp.utils.NewRecipeStatics
import com.ao.recipeapp.utils.convertBitmapToFile
import com.ao.recipeapp.utils.extensions.formattedTime
import com.ao.recipeapp.utils.getRealPathFromURI
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction0

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(
    ExperimentalPermissionsApi::class,
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType")
@Composable
fun NewRecipeScreen(
    navController: NavController, viewModel: NewRecipeViewModel = viewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val titleTextValue = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    var showBottomSheet by remember { mutableStateOf(false) }
    var servesExpanded by remember { mutableStateOf(false) }
    var cookTimeExpanded by remember { mutableStateOf(false) }
    var categoryExpanded by remember { mutableStateOf(false) }
    var textFieldCount by remember { mutableStateOf(1) }
    val textFieldValues = remember { mutableStateListOf<Pair<String, String>>() }
    val cameraPermission: PermissionState = rememberPermissionState(
        Manifest.permission.CAMERA
    )
    val hasPermission: Boolean = cameraPermission.status.isGranted
    val onRequestPermission = cameraPermission::launchPermissionRequest
    val mediaList = viewModel.medias
    val pagerState = rememberPagerState(pageCount = { mediaList.size ?: 0 })
    val coroutineScope = rememberCoroutineScope()
    var mediaType: MediaType? = null

    if (textFieldValues.size < textFieldCount) {
        textFieldValues.addAll(List(textFieldCount - textFieldValues.size) { Pair("", "") })
    }

    val selectFromGallery =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            showBottomSheet = false
            it?.let { uri ->
                val realPath = getRealPathFromURI(context, uri)
                viewModel.addMedia(
                    MediaSelectModel(
                        mediaPath = realPath ?: uri.toString(),
                        mediaType = mediaType ?: MediaType.IMAGE
                    )
                )
                coroutineScope.launch {
                    delay(500)
                    pagerState.animateScrollToPage(pagerState.pageCount - 1)
                }
            }
        }

    val takePicture =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            showBottomSheet = false
            it?.let { image ->
                val value = convertBitmapToFile(context, image)
                viewModel.addMedia(
                    MediaSelectModel(
                        mediaPath = value.absolutePath, mediaType = MediaType.IMAGE
                    )
                )
                coroutineScope.launch {
                    delay(500)
                    pagerState.scrollToPage(pagerState.pageCount - 1)
                }
            }
        }


    Box {
        KeyboardCloser {
            Scaffold(bottomBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 20.dp, shape = RectangleShape)
                        .background(Color.White)
                        .padding(20.dp)

                ) {
                    AppButton(onClick = { /*TODO*/ }, text = AppString.saveMyRecipe)
                }
            }) {
                Column(modifier = Modifier.padding(it)) {
                    TitleBar(navController)
                    if (hasPermission) {
                        Column(
                            modifier = Modifier
                                .verticalScroll(state = rememberScrollState())
                                .padding(horizontal = 20.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(bottom = 20.dp, top = 10.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .fillMaxWidth()
                                    .height(180.dp)
                                    .background(AppColor.neutral10),
                                contentAlignment = Alignment.Center
                            ) {
                                if (mediaList.isNotEmpty()) {
                                    Box {
                                        HorizontalPager(state = pagerState) { page ->
                                            val item = mediaList[page]
                                            if (item.mediaType == MediaType.VIDEO) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                                    AppVideoPlayer(videoPath = item.mediaPath)
                                                }
                                            } else {
                                                AsyncImage(
                                                    model = ImageRequest.Builder(context)
                                                        .data(mediaList[page].mediaPath).build(),
                                                    contentDescription = null,
                                                    modifier = Modifier.fillMaxSize(),
                                                    contentScale = ContentScale.FillHeight
                                                )
                                            }
                                        }
                                        Column(
                                            modifier = Modifier
                                                .padding(12.dp)
                                                .align(Alignment.TopEnd)
                                        ) {
                                            Box(modifier = Modifier
                                                .clip(CircleShape)
                                                .clickable { showBottomSheet = true }
                                                .background(Color.White)
                                                .padding(12.dp)) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.im_add_icon),
                                                    contentDescription = null,
                                                    tint = AppColor.primary,
                                                    modifier = Modifier.size(18.dp)
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Box(modifier = Modifier
                                                .clip(CircleShape)
                                                .clickable { viewModel.removeMedia(pagerState.currentPage) }
                                                .background(Color.White)
                                                .padding(12.dp)) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.im_delete_image),
                                                    contentDescription = null,
                                                    tint = AppColor.primary,
                                                    modifier = Modifier.size(18.dp)
                                                )
                                            }
                                        }
                                    }
                                } else {
                                    MediaSelectedButtons(selectFromGallery, { newMediaType ->
                                        mediaType = newMediaType
                                    }, takePicture)
                                }
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            AppTextField(
                                value = titleTextValue,
                                onValueChange = { titleText ->
                                    titleTextValue.value = titleText
                                },
                                hintText = AppString.addNewFoodDescription,
                                stringLength = 80,
                                maxLines = 2
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            AppPopupMenu(
                                expanded = servesExpanded,
                                onDismissRequest = { servesExpanded = false },
                                horizontalAlignment = Alignment.Start,
                                content = {
                                    NewRecipeStatics.serves.forEachIndexed { index, serve ->
                                        PopupMenuItem(text = {
                                            Text(
                                                text = serve.toString(),
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }, minWidth = 100.dp, onClick = {
                                            viewModel.setServesValue(serve)
                                            servesExpanded = false
                                        }, actionContent = {
                                            if (viewModel.servesValue == serve) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.ic_check),
                                                    contentDescription = null,
                                                    tint = AppColor.primary,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                        }, index = index, isAnimation = false)
                                    }
                                },
                                button = {
                                    Box(modifier = Modifier
                                        .clip(RoundedCornerShape(16.dp))
                                        .clickable { servesExpanded = true }
                                        .height(60.dp)
                                        .fillMaxWidth()
                                        .background(AppColor.neutral10),
                                        contentAlignment = Alignment.CenterStart) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Box(
                                                modifier = Modifier
                                                    .padding(horizontal = 16.dp)
                                                    .clip(RoundedCornerShape(12.dp))
                                                    .size(36.dp)
                                                    .background(Color.White),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.ic_persons),
                                                    contentDescription = null,
                                                    tint = AppColor.primary,
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                            AppText(
                                                text = AppString.serves,
                                                color = AppColor.neutral90,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                            Spacer(modifier = Modifier.weight(1f))
                                            AppText(
                                                text = viewModel.servesValue?.toString() ?: "",
                                                color = AppColor.neutral40,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Normal,
                                            )
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_next_icon),
                                                contentDescription = null,
                                                tint = Color.Black,
                                                modifier = Modifier
                                                    .padding(horizontal = 16.dp)
                                                    .size(16.dp)
                                            )
                                        }
                                    }
                                })
                            Spacer(modifier = Modifier.height(12.dp))
                            AppPopupMenu(
                                expanded = cookTimeExpanded,
                                onDismissRequest = { cookTimeExpanded = false },
                                horizontalAlignment = Alignment.Start,
                                content = {
                                    NewRecipeStatics.times.forEachIndexed { index, cooking ->
                                        PopupMenuItem(text = {
                                            Text(
                                                text = cooking.formattedTime(),
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }, minWidth = 130.dp, onClick = {
                                            viewModel.setCookingTimeValue(cooking)
                                            cookTimeExpanded = false
                                        }, actionContent = {
                                            if (viewModel.cookingTimeValue == cooking) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.ic_check),
                                                    contentDescription = null,
                                                    tint = AppColor.primary,
                                                    modifier = Modifier.size(24.dp)
                                                )
                                            }
                                        }, index = index, isAnimation = false)
                                    }
                                },
                                button = {
                                    Box(modifier = Modifier
                                        .clip(RoundedCornerShape(16.dp))
                                        .clickable { cookTimeExpanded = true }
                                        .height(60.dp)
                                        .fillMaxWidth()
                                        .background(AppColor.neutral10),
                                        contentAlignment = Alignment.CenterStart) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Box(
                                                modifier = Modifier
                                                    .padding(horizontal = 16.dp)
                                                    .clip(RoundedCornerShape(12.dp))
                                                    .size(36.dp)
                                                    .background(Color.White),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.ic_time),
                                                    contentDescription = null,
                                                    tint = AppColor.primary,
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                            AppText(
                                                text = AppString.time,
                                                color = AppColor.neutral90,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                            Spacer(modifier = Modifier.weight(1f))
                                            AppText(
                                                text = viewModel.cookingTimeValue?.formattedTime()
                                                    ?: "",
                                                color = AppColor.neutral40,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Normal,
                                            )
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_next_icon),
                                                contentDescription = null,
                                                tint = Color.Black,
                                                modifier = Modifier
                                                    .padding(horizontal = 16.dp)
                                                    .size(16.dp)
                                            )
                                        }
                                    }
                                })
                            Spacer(modifier = Modifier.height(12.dp))
                            AppPopupMenu(expanded = categoryExpanded,
                                onDismissRequest = { categoryExpanded = false },
                                horizontalAlignment = Alignment.Start,
                                content = {
                                    AppDummyData.searchCategories.forEachIndexed { index, category ->
                                        PopupMenuItem(text = {
                                            Text(
                                                text = category,
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }, onClick = {
                                            viewModel.setCategoryValue(category)
                                            categoryExpanded = false
                                        }, actionContent = {
                                            if (viewModel.categoryValue == category) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.ic_check),
                                                    contentDescription = null,
                                                    tint = AppColor.primary,
                                                    modifier = Modifier.size(24.dp)
                                                )
                                            }
                                        }, index = index, isAnimation = false)
                                    }
                                },
                                button = {
                                    Box(modifier = Modifier
                                        .clip(RoundedCornerShape(16.dp))
                                        .clickable { categoryExpanded = true }
                                        .height(60.dp)
                                        .fillMaxWidth()
                                        .background(AppColor.neutral10),
                                        contentAlignment = Alignment.CenterStart) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Box(
                                                modifier = Modifier
                                                    .padding(horizontal = 16.dp)
                                                    .clip(RoundedCornerShape(12.dp))
                                                    .size(36.dp)
                                                    .background(Color.White),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.ic_category),
                                                    contentDescription = null,
                                                    tint = AppColor.primary,
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                            AppText(
                                                text = AppString.category,
                                                color = AppColor.neutral90,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                            Spacer(modifier = Modifier.weight(1f))
                                            AppText(
                                                text = viewModel.categoryValue ?: "",
                                                color = AppColor.neutral40,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Normal,
                                            )
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_next_icon),
                                                contentDescription = null,
                                                tint = Color.Black,
                                                modifier = Modifier
                                                    .padding(horizontal = 16.dp)
                                                    .size(16.dp)
                                            )
                                        }

                                    }
                                })
                            Spacer(modifier = Modifier.height(24.dp))
                            AppText(
                                text = AppString.ingredients,
                                color = AppColor.neutral90,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Column {
                                for (i in 0 until textFieldCount) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        AppTextField(
                                            textValue = textFieldValues[i].first,
                                            hintText = AppString.ingredientName,
                                            onValueChange = {
                                                textFieldValues[i] =
                                                    textFieldValues[i].copy(first = it)
                                            },
                                            modifier = Modifier.weight(6f)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        AppTextField(
                                            textValue = textFieldValues[i].second,
                                            onValueChange = {
                                                textFieldValues[i] =
                                                    textFieldValues[i].copy(second = it)
                                            },
                                            hintText = AppString.ingredientQuantity,
                                            modifier = Modifier.weight(3f)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        IconButton(onClick = {
                                            keyboardController?.hide()
                                            focusManager.clearFocus()
                                            if (i == textFieldCount - 1) {
                                                if (textFieldValues[i].first.trim()
                                                        .isNotEmpty() && textFieldValues[i].second.trim()
                                                        .isNotEmpty()
                                                ) {
                                                    textFieldCount++
                                                }
                                            } else {
                                                textFieldValues.removeAt(i)
                                                textFieldCount--
                                            }
                                        }) {
                                            Image(
                                                painter = painterResource(id = if (i == textFieldCount - 1) R.drawable.ic_add_icon else R.drawable.ic_remove_icon),
                                                contentDescription = null
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    } else {
                        PermissionDeniedScreen(onRequestPermission)
                    }
                }
            }
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onSurface,
                shape = RectangleShape,
                dragHandle = null,
                scrimColor = Color.Black.copy(alpha = .5f),
                windowInsets = WindowInsets(0, 0, 0, 0)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = WindowInsets.safeContent
                                .asPaddingValues()
                                .calculateBottomPadding() + 12.dp
                        ), contentAlignment = Alignment.BottomCenter
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    ) {
                        MediaSelectedButtons(selectFromGallery, { newMediaType ->
                            mediaType = newMediaType
                        }, takePicture)
                    }
                }
            }
        }
    }

}

@Composable
private fun MediaSelectedButtons(
    selectFromGallery: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    mediaTypeSelected: (MediaType) -> Unit,
    takePicture: ManagedActivityResultLauncher<Void?, Bitmap?>
) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        MediaSelectionButton(
            icon = R.drawable.im_video_icon, text = AppString.video
        ) {
            selectFromGallery.launch(PickVisualMediaRequest(
                mediaType = ActivityResultContracts.PickVisualMedia.VideoOnly
            ).apply {
                mediaTypeSelected(MediaType.VIDEO)
            })
        }
        MediaSelectionButton(
            icon = R.drawable.im_camera_icon, text = AppString.camera
        ) {
            takePicture.launch(null, ActivityOptionsCompat.makeBasic())
                .apply { mediaTypeSelected(MediaType.IMAGE) }
        }
        MediaSelectionButton(
            icon = R.drawable.im_gallery_icon, text = AppString.gallery
        ) {
            selectFromGallery.launch(PickVisualMediaRequest(
                mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
            ).apply {
                mediaTypeSelected(MediaType.IMAGE)
            })
        }
    }
}


@Composable
private fun PermissionDeniedScreen(onRequestPermission: KFunction0<Unit>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.neutral10),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppText(
                text = AppString.cameraPermissionRequest,
                color = AppColor.neutral40,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextButton(
                onClick = onRequestPermission, modifier = Modifier
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
                    .background(AppColor.primary30.copy(alpha = 0.2f))
                    .height(36.dp)
                    .width(120.dp)

            ) {
                AppText(
                    text = AppString.cameraPermissionGrant,
                    style = MaterialTheme.typography.bodyMedium.copy(color = AppColor.primary),
                )
            }
        }
    }
}

@Composable
fun MediaSelectionButton(@DrawableRes icon: Int, text: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = onClick,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(AppColor.primary)
                .size(50.dp),
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        AppText(
            text = text,
            color = AppColor.neutral90,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Composable
private fun TitleBar(navController: NavController) {
    Row(
        modifier = Modifier
            .padding(start = 20.dp, end = 4.dp, bottom = 8.dp, top = 4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText(
            text = AppString.createNewRecipe,
            fontWeight = FontWeight.SemiBold,
            color = AppColor.neutral90,
            fontSize = 24.sp,
        )
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Default.Clear, contentDescription = null, modifier = Modifier.size(28.dp))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun NewRecipeScreenPreview() {
    val navController = rememberNavController()
    NewRecipeScreen(navController)
}