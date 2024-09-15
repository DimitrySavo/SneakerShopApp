package com.example.sneakershopapp.composables

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.sneakershopapp.ui.theme.customOTPCellColors
import com.example.sneakershopapp.ui.theme.customOutlinedColors

@Composable
fun OTPCodeField(modifier: Modifier = Modifier, otpLength: Int, onOtpComplete: (String) -> Unit) {
    var otpCode by remember {
        mutableStateOf("")
    }

    val focusRequesters = List(otpLength) { FocusRequester() }

    Row(
        modifier = Modifier.background(color = Color.Red).fillMaxWidth().then(modifier)
    ) {
       repeat(otpLength){ index ->
           OTPCodeCell(
               Modifier.weight(1f).padding(4.dp),
               index,
               focusRequesters,
               otpCode
           ) { }
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
            if (value.length == 1) {
                val newOtpCode = if (otpCode.length > index) {
                    otpCode.replaceRange(index, index + 1, value)
                } else {
                    otpCode + value
                }
                onOtpChange(newOtpCode)

                if (index < focusRequesters.size - 1) {
                    focusRequesters[index + 1].requestFocus()
                }
            }
        },
        singleLine = true,
        enabled = isCurrentEnabled,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        colors = customOTPCellColors(),
        textStyle = MaterialTheme.typography.labelMedium,
        modifier = Modifier
            .then(modifier)
            .clip(RoundedCornerShape(25))
            .focusRequester(focusRequesters[index])
            .focusable()
    )
}
