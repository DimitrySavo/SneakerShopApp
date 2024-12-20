package com.example.sneakershopapp.composables

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.sneakershopapp.ui.theme.customOTPCellColors

@Composable
fun OTPCodeField(modifier: Modifier = Modifier, otpLength: Int, onOtpComplete: (String) -> Unit) {
    var otpCode by remember {
        mutableStateOf("")
    }

    val focusRequesters = List(otpLength) { FocusRequester() }

    Row(
        modifier = Modifier.fillMaxWidth().then(modifier)
    ) {
       repeat(otpLength){ index ->
           OTPCodeCell(
               Modifier.weight(1f).padding(4.dp),
               index,
               focusRequesters,
               otpCode
           ) { newOtpCode ->
               otpCode = newOtpCode
               if(otpCode.length == otpLength){
                   onOtpComplete(otpCode)
               }
           }
       }

        LaunchedEffect(Unit) {
            focusRequesters[0].requestFocus()
        }
    }
}

@Composable
fun OTPCodeCell(
    modifier: Modifier = Modifier,
    index: Int,
    focusRequesters: List<FocusRequester>,
    otpCode: String,
    onOtpChange: (String) -> Unit
) {
    val isCurrentEnabled = index == otpCode.length

    OutlinedTextField(
        value = if (otpCode.length > index) otpCode[index].toString() else "",
        onValueChange = { value ->
            if (value.length == 1 && value.isDigitsOnly()) {
                val newOtpCode = buildString {
                    append(otpCode)
                    if (otpCode.length > index) {
                        set(index, value[0])  // Заменяем символ в индексе
                    } else {
                        append(value)  // Добавляем символ, если индекс совпадает с длиной
                    }
                }

                onOtpChange(newOtpCode)  // Обновляем код OTP

                // Переход на следующую ячейку
                if (index < focusRequesters.size - 1) {
                    focusRequesters[index + 1].requestFocus()
                }
            }
        },
        singleLine = true,
        enabled = isCurrentEnabled,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        colors = customOTPCellColors(),
        textStyle = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .then(modifier)
            .aspectRatio(0.5f, true) // тут темка наконец заработала, но почему то на самом маленьком preview высота блоков все еще не одинаковая
            .clip(RoundedCornerShape(25))
            .focusRequester(focusRequesters[index])
            .focusable()
    )
}
