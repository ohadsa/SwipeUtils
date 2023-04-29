package com.ohadsa.afeka

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwappableBox(
    modifier: Modifier = Modifier,
    startContentWidth: Dp = 0.dp,
    endContentWidth: Dp = 0.dp,
    state: SwipeableState<Int> = rememberSwipeableState(initialValue = 0),
    swipeDirection: SwipeDirection = SwipeDirection.EndToStart,
    startContent: @Composable (RowScope.(SwipeableState<Int>) -> Unit)? = null,
    endContent: @Composable (RowScope.(SwipeableState<Int>) -> Unit)? = null,
    content: @Composable BoxScope.(SwipeableState<Int>) -> Unit,
) {
    val startWidthPx = with(LocalDensity.current) { startContentWidth.toPx() }
    val endWidthPx = with(LocalDensity.current) { endContentWidth.toPx() }
    val anchors = when (swipeDirection) {
        SwipeDirection.StartToEnd -> mapOf(
            0f to 0,
            startWidthPx to 1
        )
        SwipeDirection.EndToStart -> mapOf(
            0f to 0,
            -endWidthPx to 1
        )
        SwipeDirection.Both -> mapOf(
            0f to 0,
            startWidthPx to 1,
            -endWidthPx to 2
        )
    }

    val offsetRange = when (swipeDirection) {
        SwipeDirection.StartToEnd -> 0f..Float.POSITIVE_INFINITY
        SwipeDirection.EndToStart -> Float.NEGATIVE_INFINITY..0f
        SwipeDirection.Both -> Float.NEGATIVE_INFINITY..Float.POSITIVE_INFINITY
    }
    val startSwipeProgress by animateFloatAsState(
        targetValue = if (state.offset.value > 0f) {
            (state.offset.value / startWidthPx).absoluteValue
        } else 0f
    )
    val endSwipeProgress by animateFloatAsState(
        targetValue = if (state.offset.value < 0f) {
            (state.offset.value / endWidthPx).absoluteValue
        } else 0f
    )
    val startContentLiveWidth = startContentWidth * startSwipeProgress
    val endContentLiveWidth = endContentWidth * endSwipeProgress
    Box(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .clipToBounds()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = when (swipeDirection) {
                SwipeDirection.StartToEnd -> Arrangement.Start
                SwipeDirection.EndToStart -> Arrangement.End
                SwipeDirection.Both -> Arrangement.SpaceBetween
            }
        ) {
            if (swipeDirection in listOf(
                    SwipeDirection.StartToEnd,
                    SwipeDirection.Both
                ) && startContent != null
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .width(startContentLiveWidth)
                        .clipToBounds()
                ) {
                    startContent(state)
                }
            }
            if (swipeDirection in listOf(
                    SwipeDirection.EndToStart,
                    SwipeDirection.Both
                ) && endContent != null
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .width(endContentLiveWidth)
                        .clipToBounds()
                ) {
                    endContent(state)
                }
            }
        } // Bottom Layer
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .offset {
                IntOffset(
                    state.offset.value
                        .coerceIn(offsetRange)
                        .toInt(), 0
                )
            }
            .swipeable(
                state = state,
                anchors = anchors,
                orientation = Orientation.Horizontal,
                thresholds = { _, _ -> FixedThreshold(12.dp) }
            )) {
            content(state)
        }
    }
}

enum class SwipeDirection {
    StartToEnd, EndToStart, Both
}