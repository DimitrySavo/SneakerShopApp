package com.example.sneakershopapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sneakershopapp.R
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme

@Composable
fun SecondHelloScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary)
        )
    ) {
        Box(
            modifier = Modifier
                .weight(1f)

        ) {
            Image(
                painter = painterResource(id = R.drawable.shoe_second_hello),
                contentScale = ContentScale.Crop,
                contentDescription = "shoe for second hello screen",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )

            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (helloImage, firstCurved) = createRefs()
                Image(painter = painterResource(id = R.drawable.background_smile),
                    contentDescription = "smile",
                    modifier = Modifier
                        .constrainAs(helloImage) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        }
                        .padding(end = 20.dp, top = 90.dp)
                        .size(45.dp)
                        .alpha(0.7f)
                )

                Image(painter = painterResource(id = R.drawable.curve_background_lines),
                    contentDescription = "curve lines on background",
                    modifier = Modifier
                        .constrainAs(firstCurved) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                        .padding(start = 40.dp, top = 100.dp)
                        .size(100.dp)
                        .alpha(0.8f)
                        .rotate(110f)
                )
            }
        }
        Box(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Начнем\nпутешествие",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(top = 40.dp, bottom = 15.dp)
                )
                Text(
                    text = "Умная, великолепная и модная\n коллекция. Изучите сейчас!",
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }

}

@Preview
@Composable
private fun Second_screen_preview() {
    SneakerShopAppTheme {
        SecondHelloScreen()
    }
}