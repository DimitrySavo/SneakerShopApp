package com.example.sneakershopapp.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sneakershopapp.ui.theme.customOutlinedColors

@Composable
fun DefaultOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = "",
    errorMessage: String = "",
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            colors = customOutlinedColors(),
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            maxLines = 1,
            placeholder = { Text(text = placeholder, style = MaterialTheme.typography.bodyMedium) }
        )
        if (errorMessage != "No message needed") {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .align(alignment = Alignment.End),

                )
        }
    }
}
