package com.example.sneakershopapp.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sneakershopapp.ui.theme.customOutlinedColors

@Composable
fun DefaultOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = "",
    onValueChange: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange() },
        colors = customOutlinedColors(),
        shape = RoundedCornerShape(20),
        modifier = modifier,
        placeholder = { Text(text = placeholder) }
    )
}
