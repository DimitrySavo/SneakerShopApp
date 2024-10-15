package com.example.sneakershopapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sneakershopapp.Paths
import com.example.sneakershopapp.composables.BackIconButton
import com.example.sneakershopapp.composables.TextFieldTopLabel
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.ProvidePadding
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme
import com.example.sneakershopapp.viewmodel.RegisterViewModel
import com.example.sneakershopapp.viewmodel.UserViewModel

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userViewModel: UserViewModel,
    registerViewModel: RegisterViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    val user by userViewModel.user.collectAsState()
    val password by userViewModel.password.collectAsState()

    val nameError by registerViewModel.nameError.collectAsState()
    val surnameError by registerViewModel.surnameError.collectAsState()
    val emailError by registerViewModel.emailError.collectAsState()
    val passwordError by registerViewModel.passwordError.collectAsState()
    val registerSate by registerViewModel.registerState.collectAsState()

    LaunchedEffect(registerSate) {
        when(registerSate){
            true -> {
                navController.navigate(Paths.STORE) {
                    popUpTo(Paths.LOGIN) { inclusive = true }
                    popUpTo(Paths.REGISTER) { inclusive = true}
                }.also {
                    registerViewModel.resetRegisterState()
                    registerViewModel.resetErrorMessages()
                }
            }
            false -> {
                Log.e("RegisterScreen", "Some error happened").also {
                    registerViewModel.resetRegisterState()
                }
            }
            else -> {}
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
            val (backButton, register, instruction, nameBlock, surnameBlock, emailBlock, passwordBlock, personalDataAgree, registerButton) = createRefs()
            BackIconButton(
                isEnabled = navController.previousBackStackEntry != null,
                modifier = Modifier
                    .constrainAs(backButton) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            ) {
                registerViewModel.resetErrorMessages()
                navController.popBackStack()
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
                    .constrainAs(nameBlock) {
                        top.linkTo(instruction.bottom)
                        start.linkTo(parent.start)
                    },
                labelText = "Ваше имя",
                fieldValue = user.name,
                placeholder = "xxxxxx",
                errorMessage = nameError,
                errorValidator = { true }
            ) {
                userViewModel.updateUser(name = it)
            }

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(surnameBlock) {
                        top.linkTo(nameBlock.bottom)
                        start.linkTo(parent.start)
                    },
                labelText = "Ваша фамилия",
                fieldValue = user.surname,
                placeholder = "xxxxxxxx",
                errorMessage = surnameError,
                errorValidator = { true }
            ) {
                userViewModel.updateUser(surname = it)
            }

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(emailBlock) {
                        top.linkTo(surnameBlock.bottom)
                        start.linkTo(parent.start)
                    },
                labelText = "E-mail",
                placeholder = "xyz@gmail.com",
                fieldValue = user.email,
                errorMessage = emailError,
                errorValidator = { true }
            ) {
                userViewModel.updateUser(email = it)
            }

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(passwordBlock) {
                        top.linkTo(emailBlock.bottom)
                        start.linkTo(parent.start)
                    },
                labelText = "Пароль",
                fieldValue = password,
                placeholder = "*******",
                errorMessage = passwordError,
                errorValidator = { true }
            ) {
                userViewModel.updatePassword(it)
            }

            Row( // тут должен быть интент на страницу в браузере с условиями использования или типа того
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
                        .clip(RoundedCornerShape(20))
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
                onClick = {
                    Log.i("RegisterButton", "Get into method")
                    if (registerViewModel.validations(user, password)) {
                        Log.i("RegisterButton", "Pass validations")
                        userViewModel.registerUser(registerViewModel)
                    }
                },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .constrainAs(registerButton) {
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

            Text(
                text = " Войти",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            navController.navigate(Paths.LOGIN) {
                                popUpTo(Paths.LOGIN) { inclusive = true } // чтобы избежать потери экрана из за убогого popUpTo можно использовать проверку текущего backstackEntry, и при наличии жкрана просто переходить на него, но эт душно и не то чтобы прям таки нужно
                            }
                            registerViewModel.resetErrorMessages()
                        }
                    )
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
        }
    }
}