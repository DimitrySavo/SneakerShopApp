package com.example.sneakershopapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.MarkEmailRead
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.ProvidePadding
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme

@Composable
fun EnterPasswordPopup(modifier: Modifier = Modifier, onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = LocalPaddingValues.current.horizontal)
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20))
                .padding(LocalPaddingValues.current.horizontal)
                .wrapContentHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.background)
                    .fillMaxWidth(0.8f)
            ) {
                Text(
                    text = "Введите пароль",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(bottom = LocalPaddingValues.current.underLabel)
                )
                Row(
                    modifier = Modifier
                        .padding(LocalPaddingValues.current.underField),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = LocalPaddingValues.current.underField)
                    ) {
                        DefaultOutlinedTextField(
                            value = "",
                            placeholder = "Пароль",
                            errorMessage = "No message needed"
                        ) {

                        }
                    }


                    IconButton(
                        modifier = Modifier
                            .size(LocalPaddingValues.current.iconSize)
                            .background(color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(15.dp)),
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Enter password",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(LocalPaddingValues.current.iconSize),
                        )
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 1024, heightDp = 1336)
@Composable
private fun PopupPreview() {
    ProvidePadding {
        SneakerShopAppTheme {
            EnterPasswordPopup() {}
        }
    }
}