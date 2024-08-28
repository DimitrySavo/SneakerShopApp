package com.example.sneakershopapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (helloImage, firstCurved, secondCurved) = createRefs()
                    Image(painter = painterResource(id = R.drawable.background_smile),
                        contentDescription = "smile",
                        modifier = Modifier
                            .constrainAs(helloImage) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            }
                            .padding(start = 50.dp, top = 50.dp)
                            .size(45.dp)
                            .alpha(0.5f)
                    )

                    Image(painter = painterResource(id = R.drawable.curve_background_lines),
                        contentDescription = "curve lines on background",
                        modifier = Modifier
                            .constrainAs(firstCurved) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                            }
                            .padding(start = 50.dp, bottom = 120.dp)
                            .size(100.dp)
                            .alpha(0.5f)
                    )

                    Image(painter = painterResource(id = R.drawable.curve_background_lines),
                        contentDescription = "curve lines on background",
                        modifier = Modifier
                            .constrainAs(secondCurved) {
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            }
                            .padding(end = 30.dp, bottom = 180.dp)
                            .size(100.dp)
                            .alpha(0.5f)
                            .rotate(90f)
                    )
                }
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