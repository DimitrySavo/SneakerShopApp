package com.example.sneakershopapp.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sneakershopapp.ui.theme.LocalPaddingValues

@Composable
fun TextFieldTopLabel(
    modifier: Modifier = Modifier,
    labelText: String = "",
    fieldValue: String,
    placeholder: String = "",
    errorMessage: String = "",
    errorValidator: (String) -> Boolean,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(bottom = LocalPaddingValues.current.underLabel)
                .align(alignment = Alignment.Start)
        )

        DefaultOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LocalPaddingValues.current.underField),
            value = fieldValue,
            placeholder = placeholder,
            errorMessage = errorMessage,
            onValueChange = onValueChange
        )
    }
}