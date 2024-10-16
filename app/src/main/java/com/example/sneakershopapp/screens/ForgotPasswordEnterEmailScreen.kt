package com.example.sneakershopapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.sneakershopapp.Paths
import com.example.sneakershopapp.composables.BackIconButton
import com.example.sneakershopapp.composables.CheckEmailPopup
import com.example.sneakershopapp.composables.DefaultOutlinedTextField
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme
import com.example.sneakershopapp.viewmodel.UserViewModel

@Composable
fun ForgotPasswordEnterEmailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userViewModel: UserViewModel,
) {
    val scrollState = rememberScrollState()

    val user by userViewModel.user.collectAsState()
    var emailMessage by rememberSaveable { mutableStateOf("") }
    var emailSentState by rememberSaveable { mutableStateOf<Boolean?>(null) }

    LaunchedEffect(emailSentState) {
        when(emailSentState){
            false -> {
                emailSentState = null
            }
            else -> {}
        }
    }

    if (emailSentState == true) {
        CheckEmailPopup {
            navController.navigate(Paths.OTP)
            emailMessage = ""
            emailSentState = null
        }
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
            val (backButton, forgotPasswordTitle, instruction, emailText, getCode) = createRefs()

            BackIconButton(
                isEnabled = navController.previousBackStackEntry != null,
                modifier = Modifier
                    .constrainAs(backButton) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            ) {
                navController.popBackStack()
            }

            Text(
                text = "Забыл пароль",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .constrainAs(forgotPasswordTitle) {
                        top.linkTo(backButton.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(bottom = LocalPaddingValues.current.underLabel)
            )
            Text(
                text = "Введите свою почту для сброса пароля",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .constrainAs(instruction) {
                        top.linkTo(forgotPasswordTitle.bottom)
                    }
                    .fillMaxWidth()
                    .padding(bottom = LocalPaddingValues.current.vertical)
            )

            DefaultOutlinedTextField(
                Modifier
                    .constrainAs(emailText) {
                        top.linkTo(instruction.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(bottom = LocalPaddingValues.current.vertical),
                user.email,
                "xyz@gmail.com",
                emailMessage,
            ) {
                userViewModel.updateUser(email = it)
            }

            Button(
                onClick = {
                    userViewModel.passwordReset(user.email, { message -> emailMessage = message } ) { status -> emailSentState = status }
                },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .constrainAs(getCode) {
                        top.linkTo(emailText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()

            ) {
                Text(
                    text = "Отправить",
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
    SneakerShopAppTheme {
        //ForgotPasswordEnterEmailScreen()
    }
}