package com.example.sneakershopapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sneakershopapp.model.Shoe
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.ProvidePadding
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme

@Composable
fun ShoeCard(shoe: Shoe, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(LocalPaddingValues.current.underLabel)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(15.dp)
            )
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .aspectRatio(0.75f)
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = LocalPaddingValues.current.underField,
                    start = LocalPaddingValues.current.underField
                )
        ) {
            IconButton(
                modifier = Modifier
                    .padding(bottom = LocalPaddingValues.current.underLabel)
                    .background(
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(15.dp)
                    ),
                onClick = {
                    //добавить в избранное
                }
            ) {
                Icon(
                    imageVector = Icons.Default.HeartBroken,
                    contentDescription = "Add to favorite",
                    tint = MaterialTheme.colorScheme.onBackground, //TODO сделать проверку на favorite
                    modifier = Modifier.size(LocalPaddingValues.current.iconSize),
                )
            }

            AsyncImage(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/05856ac7-0129-4395-bd6e-2fe2669025fb/custom-nike-dunk-low-by-you-su24.png")
                    .crossfade(true)
                    .build(),
                contentDescription = ""
            )

            Text(
                text = shoe.brandName + " " + shoe.modelName,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelMedium, //написать новый стиль с более жирным текстом(либо сделать с помощью copy)
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Left
            )

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "P " + shoe.price,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.labelSmall, //написать новый стиль с более жирным текстом(либо сделать с помощью copy)
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.Left
                )

                IconButton(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(
                                topStart = 15.dp,
                                topEnd = 0.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 15.dp
                            )
                        ),
                    onClick = {
                        //добавить в корзину (снизу открывается доп окно с размером)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Add to cart",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(LocalPaddingValues.current.iconSize),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShoeCardPreview() {
    SneakerShopAppTheme {
        ProvidePadding {
        }
    }
}