package com.example.sneakershopapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sneakershopapp.R
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme

@Composable
fun First_hello_screen(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        )
    ) {
        val (top_side_hello_effect, hello_text) = createRefs()
        val top_side_hello_effect_painter = painterResource(id = R.drawable.hello_side_effect)
        Image(
            painter = top_side_hello_effect_painter,
            contentDescription = "Hello side effect",
            modifier = Modifier
                .constrainAs(top_side_hello_effect) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(16.dp)
        )
        
        Text(text = "ДОБРО ПОЖАЛОВАТЬ", modifier = Modifier,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge)
    }
}

@Preview
@Composable
private fun first_screen_preview() {
    SneakerShopAppTheme {
        First_hello_screen()
    }
}