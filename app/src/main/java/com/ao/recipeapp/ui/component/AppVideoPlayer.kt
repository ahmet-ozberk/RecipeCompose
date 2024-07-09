package com.ao.recipeapp.ui.component

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.OptIn
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import io.sanghun.compose.video.VideoPlayer
import io.sanghun.compose.video.controller.VideoPlayerControllerConfig
import io.sanghun.compose.video.uri.VideoPlayerMediaItem

@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("WrongConstant")
@OptIn(UnstableApi::class)
@Composable
fun AppVideoPlayer(
    videoPath: String? = null,
    isDummyVideo: Boolean = false,
    onClick: ((isFullScreen: Boolean) -> Unit)? = null
) {
    val dummyVideoPath =
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
    val context = LocalContext.current
    val mediaSource = remember(if (isDummyVideo) dummyVideoPath else videoPath) {
        VideoPlayerMediaItem.NetworkMediaItem(if (isDummyVideo) dummyVideoPath else videoPath ?: "")
    }

    val mediaItems = listOf(mediaSource)

    Box(modifier = Modifier.fillMaxSize()) {
        VideoPlayer(
            mediaItems = mediaItems,
            handleLifecycle = true,
            autoPlay = false,
            usePlayerController = true,
            enablePip = false,
            enablePipWhenBackPressed = false,
            controllerConfig = VideoPlayerControllerConfig.Default.copy(
                showRepeatModeButton = true,
                showFullScreenButton = true,
                showSpeedAndPitchOverlay = true,
                showCurrentTimeAndTotalTime = true,
            ),
        )
    }
//    LaunchedEffect(mediaSource) {
//        exoPlayer.setMediaItem(mediaSource)
//        exoPlayer.prepare()
//    }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            exoPlayer.release()
//        }
//    }
//
//    AndroidView(
//        factory = { ctx ->
//            PlayerView(ctx).apply {
//                player = exoPlayer
//                setFullscreenButtonClickListener {
//                    onClick?.invoke(it)
//                }
//                layoutParams =
//                    FrameLayout.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.MATCH_PARENT
//                    )
//            }
//        },
//        modifier = Modifier
//            .fillMaxSize()
//    )
}