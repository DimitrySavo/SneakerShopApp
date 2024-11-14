package com.example.sneakershopapp.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
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
import coil.compose.AsyncImage
import com.example.sneakershopapp.composables.BackButtonMiddleLabel
import com.example.sneakershopapp.model.Shoe
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.ProvidePadding
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShoeScreen(modifier: Modifier = Modifier, shoe: Shoe, images: List<String>) {
    val scrollState = rememberScrollState()

    val pagerState = rememberPagerState {
        images.size
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                top = 10.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = LocalPaddingValues.current.vertical
            ) // TODO заменить на адаптивное значение
            .scrollable(scrollState, Orientation.Vertical),
    ) {
        BackButtonMiddleLabel(
            labelText = "Sneaker Shop",
            modifier = Modifier
                .padding(bottom = LocalPaddingValues.current.horizontal)
        ) {
            TODO("navigate back to shop screen")
        }

        Text(
            text = shoe.brandName + " " + shoe.modelName,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium, //TODO написать новый стиль с более жирным текстом(либо сделать с помощью copy)
            modifier = Modifier
                .fillMaxWidth(0.75f)
        )

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = LocalPaddingValues.current.vertical),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LocalPaddingValues.current.vertical)
        ) { page: Int ->
            AsyncImage(
                model = images[page],
                contentDescription = "image of shoe",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Text(
            text = shoe.description,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )

        // TODO выбор размеров

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f),
            ) {
                IconButton(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.onSurface,
                            shape = RoundedCornerShape(15)
                        )
                        .size(LocalPaddingValues.current.iconSize)
                        .align(Alignment.Center),
                    onClick = {
                        //добавить в избранное
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.HeartBroken,
                        contentDescription = "Add to favorite",
                        tint = MaterialTheme.colorScheme.onBackground, //TODO сделать проверку на favorite
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }

            Button(
                shape = RoundedCornerShape(20),
                onClick = {
                    TODO("Добавлять в корзину")
                },
                modifier = Modifier
                    .weight(2f)
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingBag,
                    contentDescription = "Add to favorite",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(LocalPaddingValues.current.iconSize)
                )

                Text(
                    text = "В корзину",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
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
private fun HelloPagerPreview() {
    ProvidePadding {
        SneakerShopAppTheme {
            ShoeScreen(
                shoe = Shoe(
                    brandName = "Nike",
                    modelName = "Air Max 270 Essential",
                    id = "",
                    color = "",
                    description = "Some text for description",
                    preImageUrl = "",
                    tags = emptyList(),
                    imageCollectionUrl = "",
                    sizes = emptyMap()
                ),
                images = emptyList()
            )
        }
    }
}