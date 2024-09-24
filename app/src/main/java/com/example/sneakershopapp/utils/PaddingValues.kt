package com.example.sneakershopapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class PaddingValues(
    val horizontal: Dp,
    val vertical: Dp,
    val underLabel: Dp,
    val underField: Dp,
    val profileImageSize: Dp
    )

@Composable
fun getPaddingValues(): PaddingValues {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    return when{
        screenHeight < 1000 -> PaddingValues(30.dp, 50.dp, 10.dp, 10.dp, 128.dp)
        screenHeight in 1001..1499 -> PaddingValues(40.dp, 50.dp, 15.dp, 20.dp, 256.dp)
        else -> PaddingValues(60.dp, 50.dp, 20.dp, 30.dp, 384.dp)
    }
}