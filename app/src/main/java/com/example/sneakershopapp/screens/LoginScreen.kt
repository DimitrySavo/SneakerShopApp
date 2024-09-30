package com.example.sneakershopapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.sneakershopapp.composables.BackIconButton
import com.example.sneakershopapp.composables.DefaultOutlinedTextField
import com.example.sneakershopapp.composables.PasswordTextField
import com.example.sneakershopapp.composables.TextFieldTopLabel
import com.example.sneakershopapp.ui.theme.LocalPaddingValues
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme
import com.example.sneakershopapp.viewmodel.UserViewModel

@Composable
fun LoginScreen(modifier: Modifier = Modifier) { //, navController: NavController, userViewModel: UserViewModel  после окончания верстки нужно вставить это назад в аргументы
    val scrollState = rememberScrollState()
    //val user by userViewModel.user.collectAsState()
    var user by remember {
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
            val (backButton, hello, instruction, emailBlock, passwordBlock, forgotPassword, logInButton) = createRefs()
            BackIconButton(
                isEnabled = false,
                modifier = Modifier
                    .constrainAs(backButton) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            ) {
                // Кнопочка тут просто для красоты, хз зач она на макете
            }
            Text(
                text = "Привет!",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .constrainAs(hello) {
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
                        top.linkTo(hello.bottom)
                    }
                    .fillMaxWidth()
                    .padding(bottom = LocalPaddingValues.current.underField)
            )

            TextFieldTopLabel(
                modifier = Modifier
                    .constrainAs(emailBlock){
                        top.linkTo(instruction.bottom)
                        start.linkTo(parent.start)
                    }
                ,
                labelText = "E-mail",
                fieldValue = "",
                placeholder = "xyz@gmail.com",
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
                placeholder = "*******",
                fieldValue = "",
                errorMessage = "",
                errorValidator = { true }
            ) {

            }


            TextButton(
                onClick = {},
                modifier = Modifier
                    .constrainAs(forgotPassword) {
                        top.linkTo(passwordBlock.bottom)
                        end.linkTo(parent.end)
                    }
                    .padding(bottom = LocalPaddingValues.current.vertical)
            ) {
                Text(
                    text = "Восстановить",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
            Button(
                onClick = {},
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .constrainAs(logInButton){
                        top.linkTo(forgotPassword.bottom)
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
                text = "Вы впервые?",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )

            TextButton(
                onClick = {},
                contentPadding = PaddingValues(0.dp),

            ) {
                Text(
                    text = " Создать пользователя",
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
    SneakerShopAppTheme {
        LoginScreen()
    }
}