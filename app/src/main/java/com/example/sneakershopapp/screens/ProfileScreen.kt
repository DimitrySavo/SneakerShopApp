package com.example.sneakershopapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.sneakershopapp.R
import com.example.sneakershopapp.composables.BackButtonMiddleLabel
import com.example.sneakershopapp.composables.TextFieldTopLabel
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.ProvidePadding
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 50.dp, horizontal = 30.dp)
        ) {
            val (backButtonLabel, profileImage, changeProfileDataButton, nameBlock, surnameBlock, emailBlock, deliveryAddressBlock, phoneNumberBlock) = createRefs()

            BackButtonMiddleLabel(
                modifier = Modifier
                    .constrainAs(backButtonLabel){
                        top.linkTo(parent.top)
                    },
                labelText = "Профиль"
            ) {

            }

            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("") // replace with profile image url
                        .placeholder(R.drawable.shoe_with_leg_hello)
                        .error(R.drawable.background_smile)
                        .build()
                ),
                contentDescription = "Profile image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(LocalPaddingValues.current.profileImageSize)
                    .clip(CircleShape)
                    .constrainAs(profileImage){
                        top.linkTo(backButtonLabel.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            TextButton(
                onClick = {},
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .constrainAs(changeProfileDataButton){
                        top.linkTo(profileImage.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Text(
                    text = "Изменить данные профиля",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleSmall
                )
            }

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(nameBlock){
                        top.linkTo(changeProfileDataButton.bottom)
                        start.linkTo(parent.start)
                    },
                labelText = "Имя",
                fieldValue = "",
                placeholder = "XXXXXX",
                errorMessage = "",
                errorValidator = { true },
            ) {

            }

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(surnameBlock){
                        top.linkTo(nameBlock.bottom)
                        start.linkTo(parent.start)
                    },
                labelText = "Фамилия",
                fieldValue = "",
                placeholder = "XXXXXX",
                errorMessage = "",
                errorValidator = { true },
            ) {

            }

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(emailBlock){
                        top.linkTo(surnameBlock.bottom)
                        start.linkTo(parent.start)
                    },
                labelText = "E-mail",
                fieldValue = "",
                placeholder = "xyz@gmail.com",
                errorMessage = "",
                errorValidator = { true }
            ) {

            }

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(deliveryAddressBlock){
                        top.linkTo(emailBlock.bottom)
                        start.linkTo(parent.start)
                    },
                labelText = "Адрес",
                fieldValue = "",
                placeholder = "Ваш адрес доставки",
                errorMessage = "",
                errorValidator = { true }
            ) {

            }

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(phoneNumberBlock){
                        top.linkTo(deliveryAddressBlock.bottom)
                        start.linkTo(parent.start)
                    },
                labelText = "Телефон",
                fieldValue = "",
                placeholder = "X-(XXX)-XXX-XX-XX",
                errorMessage = "",
                errorValidator = { true }
            ) {
                
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
            ProfileScreen()
        }
    }
}