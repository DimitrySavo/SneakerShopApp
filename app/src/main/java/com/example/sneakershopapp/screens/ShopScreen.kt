package com.example.sneakershopapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sneakershopapp.Paths
import com.example.sneakershopapp.composables.SearchTextField
import com.example.sneakershopapp.composables.ShoeCard
import com.example.sneakershopapp.model.Shoe
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.ProvidePadding
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme
import com.example.sneakershopapp.viewmodel.ShopViewModel

@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    list: List<Shoe>,
    navController: NavController,
    shopViewModel: ShopViewModel
) {

    LaunchedEffect(Unit) {
        shopViewModel.getFavorites()
    }
    //окно с фильтрами можно реализоват с помощью animatedVisibility
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(10.dp), //TODO поменять фиксированное значение на адаптивное, которое нужно еще добавить дата класс
        columns = GridCells.Fixed(2) //TODO задавать ширину клетки адаптивно, потому что dp
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(15.dp)
                        ),
                    onClick = {
                        navController.navigate(Paths.INFO)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Enter password",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(LocalPaddingValues.current.iconSize),
                    )
                }

                Text(
                    text = "Главная",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    textAlign = TextAlign.Center
                )

                IconButton(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.onSurface,
                            shape = CircleShape
                        ),
                    onClick = {
                        navController.navigate(Paths.CART)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingBag,
                        contentDescription = "Enter password",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(LocalPaddingValues.current.iconSize),
                    )
                }
            }
        }

        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            Row(
                modifier = Modifier
                    .padding(LocalPaddingValues.current.underField),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.85f),
                    value = ""
                ) {

                }

                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )

                IconButton(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(15.dp)
                        ),
                    onClick = {
// show filters menu
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.FilterAlt,
                        contentDescription = "Enter password",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }

        items(
            items = list
        ) { shoe ->
            ShoeCard(
                shoe = shoe,
                onClick = { navController.navigate(Paths.getShoeScreen(shoe.id)) },
                viewModel = shopViewModel
            )
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
    SneakerShopAppTheme {
        ProvidePadding {
            //ShopScreen(list = listOf(Shoe(), Shoe(), Shoe()))
        }
    }
}