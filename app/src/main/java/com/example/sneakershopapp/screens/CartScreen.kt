package com.example.sneakershopapp.screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sneakershopapp.composables.BackButtonMiddleLabel
import com.example.sneakershopapp.composables.CartItem
import com.example.sneakershopapp.model.CartShoe
import com.example.sneakershopapp.model.Shoe
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.ProvidePadding
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme

@Composable
fun CartScreen(modifier: Modifier = Modifier, listOfShoes: List<CartShoe>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        BackButtonMiddleLabel(
            labelText = "Корзина",
            modifier = Modifier
                .padding(LocalPaddingValues.current.underField)
        ) {
            TODO("Go back")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(horizontal = LocalPaddingValues.current.horizontal)
        ) {
            items(
                items = listOfShoes
            ) { item: CartShoe ->
                CartItem(quantity = item.quantity, size = item.size, shoe = Shoe(
                    brandName = "Nike",
                    modelName = "Air Max 270 Essential",
                    id = "",
                    color = "",
                    description = "Some text for description",
                    preImageUrl = "",
                    tags = emptyList(),
                    imageCollectionUrl = "",
                    sizes = emptyMap()
                ))
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = MaterialTheme.colorScheme.onSurface)
                .padding(LocalPaddingValues.current.horizontal)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = LocalPaddingValues.current.underField)
            ) {
                Text(
                    text = "Сумма",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )

                Text(
                    text = "P"
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = LocalPaddingValues.current.underField)
            ) {
                Text(
                    text = "Доставка",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )

                Text(
                    text = "P"
                )
            }

            Divider(
                modifier = Modifier
                    .padding(vertical = LocalPaddingValues.current.underField)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Итого",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )

                Text(
                    text = "P"
                )
            }

            Spacer(
                modifier = Modifier
                    .weight(1f)
            )

            Button(
                onClick = {
                    TODO("Make an order")
                },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Оформить Заказ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(vertical = (MaterialTheme.typography.bodyMedium.fontSize.value.dp / 2))
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
            CartScreen(
                listOfShoes = listOf(CartShoe(size = "10 US", shoeRef = null))
            )
        }
    }
}