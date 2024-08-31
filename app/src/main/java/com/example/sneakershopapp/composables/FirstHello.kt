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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sneakershopapp.R
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme

@Composable
fun FirstHelloScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary)
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.shoe_with_leg_hello),
            contentDescription = "shoe with leg",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )

        Column {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (topSideHelloEffect, bottomHelloEffect, helloTextGood, helloTextGreetings) = createRefs()
                    Image(
                        painter = painterResource(id = R.drawable.hello_side_effect),
                        contentDescription = "Hello side effect",
                        modifier = Modifier
                            .constrainAs(topSideHelloEffect) {
                                bottom.linkTo(helloTextGreetings.top)
                                start.linkTo(helloTextGreetings.start)
                            }
                            .padding(bottom = 25.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.hello_under_effect),
                        contentDescription = "Hello bottom effect",
                        modifier = Modifier
                            .constrainAs(bottomHelloEffect) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .padding(bottom = 30.dp)
                    )

                    Text(text = "ДОБРО",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .constrainAs(helloTextGood) {
                                bottom.linkTo(helloTextGreetings.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )

                    Text(text = "ПОЖАЛОВАТЬ",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge, modifier = Modifier
                            .constrainAs(helloTextGreetings) {
                                bottom.linkTo(bottomHelloEffect.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .padding(bottom = 10.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth()
            ) {

            }
        }
    }
}

@Preview
@Composable
private fun first_screen_preview() {
    SneakerShopAppTheme {
        FirstHelloScreen()
    }
}