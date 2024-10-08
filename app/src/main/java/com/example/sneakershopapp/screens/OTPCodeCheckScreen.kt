package com.example.sneakershopapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sneakershopapp.composables.BackIconButton
import com.example.sneakershopapp.composables.OTPCodeField
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.ProvidePadding
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme

@Composable
fun OTPCodeCheckScreen(modifier: Modifier = Modifier) { //, navController: NavController, userViewModel: UserViewModel  после окончания верстки нужно вставить это назад в аргументы
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
                    .padding(vertical = LocalPaddingValues.current.vertical, horizontal = LocalPaddingValues.current.horizontal)
            ) {
                val (backButton, OTPCheckLabel, instruction, OTPCodeLabel, OTPField, newCodeCounter) = createRefs()

                BackIconButton(
                    isEnabled = false,
                    modifier = Modifier
                        .constrainAs(backButton) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                ) {
                    // Через navControoler делает popBackStack и возвращает на экран login
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
                OTPCodeField(otpLength = 6, modifier = Modifier
                    .constrainAs(OTPField) {
                        top.linkTo(OTPCodeLabel.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(bottom = LocalPaddingValues.current.underField)
                ) {

                }
                Text(
                    text = "Отправить новый код через: ",
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .constrainAs(newCodeCounter) {
                            start.linkTo(parent.start)
                            top.linkTo(OTPField.bottom)
                        }
                        .fillMaxWidth()
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
    ProvidePadding {
        SneakerShopAppTheme {
            OTPCodeCheckScreen()
        }
    }
}