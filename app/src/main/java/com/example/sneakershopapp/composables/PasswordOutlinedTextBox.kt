package com.example.sneakershopapp.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.example.sneakershopapp.ui.theme.customOutlinedColors

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = "",
    onValueChange: () -> Unit
) {
    var isShown by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange() },
        colors = customOutlinedColors(),
        textStyle = MaterialTheme.typography.bodyMedium,
        shape = RoundedCornerShape(20),
        modifier = modifier,
        placeholder = { Text(text = placeholder, style = MaterialTheme.typography.bodyMedium) },
        trailingIcon = {
            Icon(
                imageVector = if (isShown) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                contentDescription = if (isShown) "Hide password" else "Show password",
                tint = Color.Gray,
                modifier = Modifier.clickable { isShown = !isShown }
            )
        },
        visualTransformation = if(isShown) VisualTransformation.None else PasswordVisualTransformation()
    )
}