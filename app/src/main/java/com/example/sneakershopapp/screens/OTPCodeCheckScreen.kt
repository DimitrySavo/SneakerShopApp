package com.example.sneakershopapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.sneakershopapp.Paths
import com.example.sneakershopapp.composables.BackIconButton
import com.example.sneakershopapp.composables.DefaultOutlinedTextField
import com.example.sneakershopapp.composables.OTPCodeField
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.ProvidePadding
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme
import com.example.sneakershopapp.utils.FormattingUtils
import com.example.sneakershopapp.viewmodel.UserViewModel

@Composable
fun OTPCodeCheckScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userViewModel: UserViewModel
) {
    val scrollState = rememberScrollState()

    val otpTimer by userViewModel.otpCodeTimer.collectAsState()
    var errorMessage by remember {
        mutableStateOf("")
    }
    var otpCode by remember {
        mutableStateOf("")
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
            val (backButton, OTPCheckLabel, instruction, OTPCodeLabel, OTPField, newCodeCounter) = createRefs()

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
                text = "OTP проверка",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .constrainAs(OTPCheckLabel) {
                        top.linkTo(backButton.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(bottom = LocalPaddingValues.current.underLabel)
            )
            Text(
                text = "Пожайлуста, проверьте свою электронную почту, чтобы увидеть код подтверждения",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .constrainAs(instruction) {
                        top.linkTo(OTPCheckLabel.bottom)
                    }
                    .fillMaxWidth()
                    .padding(bottom = LocalPaddingValues.current.vertical)
            )
            Text(
                text = "OTP Код",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .constrainAs(OTPCodeLabel) {
                        start.linkTo(parent.start)
                        top.linkTo(instruction.bottom)
                    }
                    .padding(bottom = LocalPaddingValues.current.underLabel, start = 20.dp)
            )
            //Пока что заменено поле ввода на обычный текст. В последствии нужно будет разобраться с otpField и использовать его как и в мокапе.

            DefaultOutlinedTextField(
                modifier = Modifier
                    .constrainAs(OTPField) {
                        start.linkTo(parent.start)
                        top.linkTo(OTPCodeLabel.bottom)
                    },
                value = otpCode,
                placeholder = "XXXXXX",
                errorMessage = errorMessage,
            ) {
                if (it.length <= 6) {
                    otpCode = it
                    if (it.length == 6) {
                        userViewModel.checkOtpCode(otpCode, {
                            navController.navigate(Paths.NEW_PASSWORD) {
                                popUpTo(Paths.OTP) { inclusive = true }
                            }
                        }) {
                            errorMessage = "Код введен неправильно"
                            otpCode = ""
                            errorMessage = ""
                        }
                    }
                }
            }

            Box(modifier = Modifier
                .constrainAs(newCodeCounter) {
                    start.linkTo(parent.start)
                    top.linkTo(OTPField.bottom)
                }
                .fillMaxWidth()
            ) {
                if (otpTimer > 0L) {
                    Text(
                        text = "Отправить новый код через: ${
                            FormattingUtils.formatLongTimerToMinSec(
                                otpTimer
                            )
                        }",
                        textAlign = TextAlign.Left,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                } else {
                    Text(
                        text = "Отправить еще раз",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                userViewModel.passwordReset(setErrorMessage = {
                                    errorMessage = it
                                }) {}
                            }
                    )
                }
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