package com.example.sneakershopapp.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme

@Composable
fun BackButtonMiddleLabel(modifier: Modifier = Modifier, labelText: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        BackIconButton(
            isEnabled = true,
            height = 40.dp,
            width = 40.dp,
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
        ) {
            onClick()
        }

        Text(
            text = labelText,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(alignment = Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun BackWithLabelPrev() {
    SneakerShopAppTheme {
        BackButtonMiddleLabel(labelText = "Profile") { }
    }
}