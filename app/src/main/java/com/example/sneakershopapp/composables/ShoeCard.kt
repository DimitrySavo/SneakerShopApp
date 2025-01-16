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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sneakershopapp.model.Shoe
import com.example.sneakershopapp.model.getPreviewUrl
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.ProvidePadding
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme
import com.example.sneakershopapp.viewmodel.ShopViewModel

@Composable
fun ShoeCard(
    shoe: Shoe,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    viewModel: ShopViewModel
) {
    val favorites by viewModel.favorites.collectAsState()

    var imageUrl by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        imageUrl = shoe.getPreviewUrl()
    }

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
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp)),
                onClick = {
                    if(favorites.contains(shoe.id)) {
                        viewModel.unmarkShoeAsFavorite(shoeId = shoe.id)
                    } else {
                        viewModel.markShoeAsFavorite(shoeId = shoe.id)
                    }
                }
            ) {
                Icon(
                    modifier = Modifier
                        .scale(1.3f),
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Add to favorite",
                    tint = if (favorites.contains(shoe.id)) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground,
                )
            }

            AsyncImage(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = ""
            )

            Text(
                text = shoe.brandName + " " + shoe.modelName,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold), //написать новый стиль с более жирным текстом(либо сделать с помощью copy)
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp, start = LocalPaddingValues.current.underField),
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
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Normal), //написать новый стиль с более жирным текстом(либо сделать с помощью copy)
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 5.dp, start = LocalPaddingValues.current.underField),
                    textAlign = TextAlign.Left
                )
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