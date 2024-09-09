package com.example.sneakershopapp.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun BackIconButton(modifier: Modifier = Modifier, isEnabled: Boolean = true, height: Dp, width: Dp, onClick: () -> Unit) {
    IconButton(modifier = modifier, onClick = onClick, colors = IconButtonDefaults.iconButtonColors(
        contentColor = MaterialTheme.colorScheme.onBackground,
        disabledContentColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
    )) {
        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Button to go back to previous screen")
    }
}