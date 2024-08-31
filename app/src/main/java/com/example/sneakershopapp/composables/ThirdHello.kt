package com.example.sneakershopapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sneakershopapp.R
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme

@Composable
fun ThirdHelloScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        )
    ) {
        Box(
            modifier = Modifier
                .weight(1.3f)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.shoe_third_hello),
                contentScale = ContentScale.Fit,
                contentDescription = "shoe without leg for third hello screen",
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.BottomCenter)
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            Column {
                Text(
                    text = "У Вас Есть Сила\nЧтобы", style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(top = 40.dp, bottom = 15.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = "В вашей комнате много красивых и\n привлекательных растений",
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}


@Preview(device = "spec:width=411dp,height=891dp,dpi=420") // Pixel 4
@Preview(device = "spec:width=360dp,height=740dp,dpi=320") // Nexus 5
@Preview(device = "spec:width=320dp,height=480dp,dpi=160") // Small Phone
@Preview(device = "spec:width=600dp,height=1024dp,dpi=240") // 7-inch Tablet
@Preview(device = "spec:width=800dp,height=1280dp,dpi=240") // 10-inch Tablet
@Preview(device = "spec:width=1024dp,height=1366dp,dpi=264") // iPad Pro 10.5
@Preview(device = "spec:width=1440dp,height=2560dp,dpi=560") // Pixel XL
@Preview(device = "spec:width=1080dp,height=1920dp,dpi=480") // Full HD Phone
@Preview(device = "spec:width=1440dp,height=2960dp,dpi=560") // Galaxy S8
@Preview(device = "spec:width=768dp,height=1024dp,dpi=160") // Nexus 7
@Composable
private fun Second_screen_preview() {
    SneakerShopAppTheme {
        ThirdHelloScreen()
    }
}