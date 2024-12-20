package com.example.sneakershopapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.window.DialogProperties
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.ProvidePadding
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme

@Composable
fun CheckEmailPopup(modifier: Modifier = Modifier, onDismiss: () -> Unit) {
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
                // тут такая рамочка имеется, которую почти не видно, но нужно пофиксить, а то как то не круто
            ) {
                Box(
                    modifier = Modifier
                        .padding(bottom = LocalPaddingValues.current.underField)
                        .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                        .padding(15.dp)
                ){
                    Icon(
                        imageVector = Icons.Default.MarkEmailRead,
                        contentDescription = "sended message",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(LocalPaddingValues.current.iconSize)
                    )
                }

                Text(
                    text = "Проверьте ваш Email",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(bottom = LocalPaddingValues.current.underLabel)
                )

                Text(
                    text = "Мы отправили письмо с ссылкой для ввода новго пароля на вашу почту",
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(widthDp = 1024, heightDp = 1336)
@Composable
private fun PopupPreview() {
    ProvidePadding {
        SneakerShopAppTheme {
            CheckEmailPopup() {}
        }
    }
}