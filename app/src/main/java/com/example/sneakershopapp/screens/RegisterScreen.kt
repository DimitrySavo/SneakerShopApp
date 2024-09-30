package com.example.sneakershopapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.test.services.storage.file.PropertyFile.Column
import com.example.sneakershopapp.composables.BackIconButton
import com.example.sneakershopapp.composables.DefaultOutlinedTextField
import com.example.sneakershopapp.composables.PasswordTextField
import com.example.sneakershopapp.composables.TextFieldTopLabel
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.ProvidePadding
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme

@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {//, navController: NavController, userViewModel: UserViewModel  после окончания верстки нужно вставить это назад в аргументы
    val scrollState = rememberScrollState()

    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
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
                .padding(vertical = LocalPaddingValues.current.vertical, horizontal = LocalPaddingValues.current.horizontal)
        ) {
            val (backButton, register, instruction, nameBlock, surnameBlock, emailBlock, passwordBlock, personalDataAgree, registerButton) = createRefs()
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
                text = "Регистрация",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .constrainAs(register) {
                        top.linkTo(backButton.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(bottom = LocalPaddingValues.current.underLabel)
            )
            Text(
                text = "Заполните Свои данные или продолжите через социальные медиа",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .constrainAs(instruction) {
                        top.linkTo(register.bottom)
                    }
                    .fillMaxWidth()
                    .padding(bottom = LocalPaddingValues.current.underField)
            )

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(nameBlock){
                        top.linkTo(instruction.bottom)
                        start.linkTo(parent.start)
                    },
                labelText = "Ваше имя",
                fieldValue = "",
                placeholder = "xxxxxx",
                errorMessage = "",
                errorValidator = { true }
            ) { }

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(surnameBlock){
                        top.linkTo(nameBlock.bottom)
                        start.linkTo(parent.start)
                    },
                labelText = "Ваша фамилия",
                fieldValue = "",
                placeholder = "xxxxxxxx",
                errorMessage = "",
                errorValidator = { true }
            ) { }

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(emailBlock){
                        top.linkTo(surnameBlock.bottom)
                        start.linkTo(parent.start)
                    },
                labelText = "E-mail",
                placeholder = "xyz@gmail.com",
                fieldValue = "",
                errorMessage = "",
                errorValidator = { true }
            ) { }

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(passwordBlock){
                        top.linkTo(emailBlock.bottom)
                        start.linkTo(parent.start)
                    },
                labelText = "Пароль",
                fieldValue = "",
                placeholder = "*******",
                errorMessage = "",
                errorValidator = { true }
            ) { }

            Row(
                modifier = Modifier
                    .constrainAs(personalDataAgree) {
                        start.linkTo(parent.start)
                        top.linkTo(passwordBlock.bottom)
                    }
                    .padding(bottom = LocalPaddingValues.current.underField),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(
                        RoundedCornerShape(20)
                    )
                        .background(MaterialTheme.colorScheme.onSurface)
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(5.dp)
                            .size(LocalPaddingValues.current.smallIconSize)
                    )
                }
                Text(
                    text = "Даю согласие на обработку персональных данных",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiary,
                    textDecoration = TextDecoration.Underline
                )
            }

            Button(
                onClick = {},
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .constrainAs(registerButton){
                        top.linkTo(personalDataAgree.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()

            ) {
                Text(
                    text = "Зарегистрироваться",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(vertical = (MaterialTheme.typography.bodyMedium.fontSize.value.dp / 2))
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalPaddingValues.current.underField),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Есть аккаунт?",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )

            TextButton(
                onClick = {},
                contentPadding = PaddingValues(0.dp)
                ) {
                Text(
                    text = " Войти",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
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
            RegisterScreen()
        }
    }
}