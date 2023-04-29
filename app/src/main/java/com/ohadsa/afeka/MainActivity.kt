package com.ohadsa.afeka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kevinnzou.compose.composeswipebox.*
import com.ohadsa.afeka.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.padding(horizontal = 12.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            SwipeBoxAtEnd()
            Spacer(modifier = Modifier.height(8.dp))
            SwipeBoxAtStart()
            Spacer(modifier = Modifier.height(8.dp))
            SwipeBoxAtEnd2()
            Spacer(modifier = Modifier.height(8.dp))
            SwipeBoxAtBoth()
            Spacer(modifier = Modifier.height(8.dp))
            SwipeBoxWithText()
            Spacer(modifier = Modifier.height(8.dp))
        }

        CostumeDivider()
        SwipeBoxAtEnd(true)
        CostumeDivider()
        SwipeBoxAtStart(true)
        CostumeDivider()
        SwipeBoxAtEnd2(true)
        CostumeDivider()
        SwipeBoxAtBoth(true)
        CostumeDivider()
        SwipeBoxWithText(noBackground = true)

    }
}

@Composable
fun CostumeDivider(
    modifier: Modifier = Modifier,
    height: Dp = 1.dp,
    color: Color =Color(0xffDFE0DF)
) {
    Box(modifier = modifier) {
        Spacer(
            modifier = Modifier
                .height(height)
                .fillMaxWidth()
                .background(color)
        )
    }
}