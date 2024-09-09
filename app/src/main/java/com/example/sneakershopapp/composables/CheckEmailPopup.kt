package com.example.sneakershopapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MarkEmailRead
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme

@Composable
fun CheckEmailPopup(modifier: Modifier = Modifier, onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20))
                .padding(20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(10.dp)
                // тут такая рамочка имеется, которую почти не видно, но нужно пофиксить, а то как то не круто
            ) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 25.dp)
                        .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                        .padding(15.dp)
                ){
                    Icon(
                        imageVector = Icons.Default.MarkEmailRead,
                        contentDescription = "sended message",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Text(
                    text = "Проверьте ваш Email",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                )

                Text(
                    text = "Мы отправили код восстановления пароля на вашу электронную почту",
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(widthDp = 411, heightDp = 891)
@Composable
private fun PopupPreview() {
    SneakerShopAppTheme {
        CheckEmailPopup() {}
    }
}