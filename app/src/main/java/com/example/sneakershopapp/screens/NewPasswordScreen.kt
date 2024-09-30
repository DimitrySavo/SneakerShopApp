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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sneakershopapp.composables.BackIconButton
import com.example.sneakershopapp.composables.PasswordTextField
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme

@Composable
fun NewPasswordScreen(modifier: Modifier = Modifier) { //, navController: NavController, userViewModel: UserViewModel  после окончания верстки нужно вставить это назад в аргументы
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
            val (backButton, newPasswordTitle, newPasswordLabel, newPasswordField, repeatNewPasswordLabel, repeatNewPasswordField, approveButton) = createRefs()

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
                text = "Придумайте новый пароль",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .constrainAs(newPasswordTitle) {
                        top.linkTo(backButton.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(bottom = LocalPaddingValues.current.vertical)
            )
            Text(
                text = "Новый пароль: ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .constrainAs(newPasswordLabel) {
                        top.linkTo(newPasswordTitle.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(bottom = LocalPaddingValues.current.underLabel)
            )

            PasswordTextField(
                modifier = Modifier
                    .constrainAs(newPasswordField) {
                        top.linkTo(newPasswordLabel.bottom)
                        start.linkTo(parent.start)
                    }
                    .fillMaxWidth()
                    .padding(bottom = LocalPaddingValues.current.underField),
                value = "",
                placeholder = "Пароль"
            ) { }

            Text(
                text = "Повторите пароль: ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .constrainAs(repeatNewPasswordLabel) {
                        top.linkTo(newPasswordField.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(bottom = LocalPaddingValues.current.underLabel)
            )

            PasswordTextField(
                modifier = Modifier
                    .constrainAs(repeatNewPasswordField) {
                        top.linkTo(repeatNewPasswordLabel.bottom)
                        start.linkTo(parent.start)
                    }
                    .fillMaxWidth()
                    .padding(bottom = LocalPaddingValues.current.vertical),
                value = "",
                placeholder = "Пароль"
            ) { }

            Button(
                onClick = {},
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .constrainAs(approveButton){
                        top.linkTo(repeatNewPasswordField.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()

            ) {
                Text(
                    text = "Войти",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(vertical = (MaterialTheme.typography.bodyMedium.fontSize.value.dp/2))
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
        NewPasswordScreen()
    }
}