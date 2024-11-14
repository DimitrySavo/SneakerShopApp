package com.example.sneakershopapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sneakershopapp.model.Shoe
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.ProvidePadding
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme

@Composable
fun CartItem(modifier: Modifier = Modifier, shoe: Shoe, quantity: Long, size: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = Modifier
            .shadow(elevation = 4.dp)
            .fillMaxWidth()
            .aspectRatio(2.5f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp) // TODO замени на адаптивную
        ) {
            AsyncImage(
                model = shoe.preImageUrl,
                contentDescription = "preview of shoe",
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(15)
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = LocalPaddingValues.current.underField)
                    .weight(2f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = shoe.modelName,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.labelMedium
                )

                Text(
                    text = shoe.price.toString() + " x " + quantity,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}


@Preview
@Composable
private fun CartItemPreview() {
    SneakerShopAppTheme {
        ProvidePadding {
            CartItem(
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
                quantity = 1,
                size = "13 US"
            )
        }
    }
}