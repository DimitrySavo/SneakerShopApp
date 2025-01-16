package com.example.sneakershopapp.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.sneakershopapp.R
import com.example.sneakershopapp.composables.BackButtonMiddleLabel
import com.example.sneakershopapp.composables.TextFieldTopLabel
import com.example.sneakershopapp.model.User
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.ProvidePadding
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme
import com.example.sneakershopapp.viewmodel.ProfileViewModel
import com.example.sneakershopapp.viewmodel.UserViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userViewModel: UserViewModel,
    profileViewModel: ProfileViewModel = viewModel()
) {
    val scrollState = rememberScrollState()

    val userFromViewModel by userViewModel.user.collectAsState()

    val profileImageUri by profileViewModel.profileImageUri.collectAsState()

    val nameError by profileViewModel.nameError.collectAsState()
    val surnameError by profileViewModel.surnameError.collectAsState()
    val emailError by profileViewModel.emailError.collectAsState()
    val phoneError by profileViewModel.phoneNumberError.collectAsState()

    var user by remember {
        mutableStateOf(User())
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        profileViewModel.changeProfileImageUri(it)
    }

    LaunchedEffect(Unit) {
        user = userViewModel.user.value
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = LocalPaddingValues.current.vertical,
                    horizontal = LocalPaddingValues.current.horizontal
                )
        ) {
            val (backButtonLabel, profileImage, userName, changeProfileDataButton, nameBlock, surnameBlock, deliveryAddressBlock, phoneNumberBlock, saveButton) = createRefs()

            BackButtonMiddleLabel(
                modifier = Modifier
                    .constrainAs(backButtonLabel) {
                        top.linkTo(parent.top)
                    },
                labelText = "Профиль"
            ) {
                navController.popBackStack()
            }

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(profileImageUri)
                    .placeholder(R.drawable.background_smile)
                    .error(R.drawable.background_smile)
                    .build(),
                contentDescription = "Profile image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(LocalPaddingValues.current.profileImageSize)
                    .clip(CircleShape)
                    .constrainAs(profileImage) {
                        top.linkTo(backButtonLabel.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = "${userFromViewModel.name} ${userFromViewModel.surname}",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .constrainAs(userName) {
                        top.linkTo(profileImage.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(vertical = LocalPaddingValues.current.underField)
            )

            Text(
                text = "Изменить фото профиля",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .constrainAs(changeProfileDataButton) {
                        top.linkTo(userName.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(
                        vertical = LocalPaddingValues.current.underField
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            photoPickerLauncher.launch(
                                input = PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                    )
            )

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(nameBlock) {
                        top.linkTo(changeProfileDataButton.bottom)
                        start.linkTo(parent.start)
                    },
                labelText = "Имя",
                fieldValue = user.name,
                placeholder = "XXXXXX",
                errorMessage = nameError,
                errorValidator = { true },
            ) {
                user = user.copy(name = it)
            }

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(surnameBlock) {
                        top.linkTo(nameBlock.bottom)
                        start.linkTo(parent.start)
                    },
                labelText = "Фамилия",
                fieldValue = user.surname,
                placeholder = "XXXXXX",
                errorMessage = surnameError,
                errorValidator = { true },
            ) {
                user = user.copy(surname = it)
            }

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(deliveryAddressBlock) {
                        top.linkTo(surnameBlock.bottom)
                        start.linkTo(parent.start)
                    },
                labelText = "Адрес",
                fieldValue = user.deliveryAddress ?: "",
                placeholder = "Ваш адрес доставки",
                errorMessage = "",
                errorValidator = { true }
            ) {
                user = user.copy(deliveryAddress = it)
            }

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(phoneNumberBlock) {
                        top.linkTo(deliveryAddressBlock.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(bottom = LocalPaddingValues.current.underField),
                labelText = "Телефон",
                fieldValue = user.phoneNumber ?: "",
                placeholder = "X-(XXX)-XXX-XX-XX",
                errorMessage = phoneError,
                errorValidator = { true }
            ) {
                user = user.copy(phoneNumber = it)
            }

            Button(
                onClick = {
                    if (profileViewModel.validations(user)) {
                        userViewModel.changeUserData(user)
                    }
                },
                shape = RoundedCornerShape(20),
                enabled = user != userFromViewModel,
                modifier = Modifier
                    .constrainAs(saveButton) {
                        top.linkTo(phoneNumberBlock.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .alpha(
                        if (user != userFromViewModel) 1f else 0.6f
                    )
            ) {
                Text(
                    text = "Сохранить",
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
        }
    }
}