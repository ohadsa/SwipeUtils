package com.ohadsa.afeka

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ohadsa.afeka.Composable.TextWrapper
import kotlinx.coroutines.launch

@Composable
fun MyButton(text: String, noBackground: Boolean, onClick: (() -> Unit)? = null) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background( if (noBackground) Color.Transparent else Color(0xff02488F))
            .clickable(enabled = onClick != null) {
                onClick?.invoke()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text, color = if(noBackground ) Color.DarkGray else Color.White, fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun RowScope.DeleteIcon(onClick: () -> Unit) {
    Icon(
        imageVector = Icons.Outlined.Delete,
        contentDescription = "Delete",
        tint = Color.White,
        background = Color(0xff258750),
        weight = 1f,
        iconSize = 20.dp
    ) {
        onClick()
    }
}

@Composable
fun RowScope.FavoriteIcon(onClick: () -> Unit) {
    Icon(
        imageVector = Icons.Outlined.Favorite,
        contentDescription = "Favorite",
        tint = Color.White,
        background = Color(0xffF8C5BF),
        weight = 1f,
        iconSize = 20.dp
    ) {
        onClick()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeBoxAtEnd(noBackground: Boolean = false) {
    val coroutineScope = rememberCoroutineScope()
    SwappableBox(
        modifier = Modifier.fillMaxWidth(),
        swipeDirection = SwipeDirection.EndToStart,
        endContentWidth = 60.dp,
        endContent = { state ->
            DeleteIcon {
                coroutineScope.launch {
                    state.animateTo(0)
                }
            }
        }
    ) { MyButton("Swipe Left", noBackground) }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeBoxAtStart(noBackground: Boolean = false) {
    val coroutineScope = rememberCoroutineScope()
    SwappableBox(
        modifier = Modifier.fillMaxWidth(),
        swipeDirection = SwipeDirection.StartToEnd,
        startContentWidth = 60.dp,
        startContent = { state ->
            FavoriteIcon {
                coroutineScope.launch {
                    state.animateTo(0)
                }
            }
        }
    ) { MyButton("Swipe Right",noBackground) }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeBoxAtEnd2(noBackground: Boolean = false) {
    val coroutineScope = rememberCoroutineScope()
    SwappableBox(
        modifier = Modifier.fillMaxWidth(),
        swipeDirection = SwipeDirection.EndToStart,
        endContentWidth = 120.dp,
        endContent = { state ->
            FavoriteIcon {
                coroutineScope.launch {
                    state.animateTo(0)
                }
            }
            DeleteIcon {
                coroutineScope.launch {
                    state.animateTo(0)
                }
            }
        }
    ) { MyButton("Swipe Left Two Icons", noBackground) }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeBoxAtBoth(noBackground: Boolean = false) {
    val coroutineScope = rememberCoroutineScope()
    SwappableBox(
        modifier = Modifier.fillMaxWidth(),
        swipeDirection = SwipeDirection.Both,
        startContentWidth = 60.dp,
        startContent = { state ->
            FavoriteIcon {
                coroutineScope.launch {
                    state.animateTo(0)
                }
            }
        },
        endContentWidth = 60.dp,
        endContent = { state ->
            DeleteIcon {
                coroutineScope.launch {
                    state.animateTo(0)
                }
            }
        }
    ) { MyButton("Swipe Both Directions",noBackground) }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeBoxWithText(

    id: Int = 0,
    onDelete: (Int) -> Unit = {},
    noBackground: Boolean = false,
    onSwipeStateChanged: (SwipeableState<Int>) -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()
    val swipeableState = remember(id) {
        SwipeableState(0)
    }
    SwappableBox(
        modifier = Modifier.fillMaxWidth(),
        state = swipeableState,
        swipeDirection = SwipeDirection.EndToStart,
        endContentWidth = 140.dp,
        endContent = { state ->
            TextWrapper(background = Color(0xff09918d),
                weight = 1f,
                onClick = {
                    coroutineScope.launch {
                        state.animateTo(0)
                    }
                }) {
                Text(
                    text = "OK",
                    modifier = Modifier.requiredWidth(42.dp),
                    fontSize = 12.sp, maxLines = 2,
                    textAlign = TextAlign.Center, color = Color.White, fontWeight = FontWeight.Bold,
                )
            }
            TextWrapper(background = Color(0xffAF2BBF),
                weight = 1f,
                onClick = {
                    coroutineScope.launch {
                        swipeableState.animateTo(0)
                        onDelete(id)
                    }
                }) {
                Text(
                    text = "X",
                    modifier = Modifier.requiredWidth(28.dp),
                    fontSize = 12.sp, maxLines = 1,
                    textAlign = TextAlign.Center, color = Color.White, fontWeight = FontWeight.Bold,
                )
            }
        }
    ) {
        LaunchedEffect(swipeableState.targetValue) {
            onSwipeStateChanged(swipeableState)
        }
        MyButton("Swipe Left Text $id",noBackground)
    }
}
