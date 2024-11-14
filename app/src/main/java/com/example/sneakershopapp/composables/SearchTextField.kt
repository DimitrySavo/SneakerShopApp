package com.example.sneakershopapp.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sneakershopapp.ui.theme.ProvidePadding
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme
import com.example.sneakershopapp.ui.theme.customOutlinedColors

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        colors = customOutlinedColors(),
        textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
        shape = RoundedCornerShape(20),
        modifier = Modifier
            .then(modifier)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(20)),
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Поиск", style = MaterialTheme.typography.bodyMedium) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search for items",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun searchPreview() {
    ProvidePadding {
        SneakerShopAppTheme {
            SearchTextField(value = "") { }
        }
    }
}