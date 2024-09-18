package com.example.sneakershopapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sneakershopapp.R

val Roboto = FontFamily(
    Font(R.font.roboto_black, FontWeight.Black, FontStyle.Normal),
    Font(R.font.roboto_black_italic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.roboto_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.roboto_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.roboto_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.roboto_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.roboto_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.roboto_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.roboto_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.roboto_thin, FontWeight.Thin, FontStyle.Normal),
    Font(R.font.roboto_thin_italic, FontWeight.Thin, FontStyle.Italic)
)

@Composable
fun multipliedTypography(multiplier: Float = getFontMultiplier()) : Typography {
    // Set of Material typography styles to start with
    return Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp * multiplier,
            lineHeight = 24.sp * multiplier,
            letterSpacing = 0.5.sp
        ),
        titleLarge = TextStyle(                 //используется для заголовков экранов типа "регистрация, вход" и подобные.
            fontFamily = Roboto,
            fontWeight = FontWeight.Black,
            fontSize = 30.sp * multiplier,
            lineHeight = 36.sp * multiplier,
            letterSpacing = 0.3.sp
        ),
        titleMedium = TextStyle(                //используется для надписи на третьем приветственном экране
            fontFamily = Roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 30.sp * multiplier,
            lineHeight = 36.sp * multiplier,
            letterSpacing = 0.5.sp
        ),
        titleSmall = TextStyle(                 //используется для надписей под заголовками
            fontFamily = Roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp * multiplier,
            lineHeight = 22.sp * multiplier,
            letterSpacing = 0.2.sp
        ),
        labelSmall = TextStyle(                 //Надписи на втором и третьем приветственном экранах
            fontFamily = Roboto,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp * multiplier,
            lineHeight = 20.sp * multiplier,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(                 //используется для надписей на кнопках
            fontFamily = Roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp * multiplier,
            lineHeight = 22.sp * multiplier,
            letterSpacing = 0.2.sp
        ),
        labelMedium = TextStyle(                 //используется для заголовков, у которых шрифт такого же размера, что и у текста под ним со стилем bodyMedium
            fontFamily = Roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp * multiplier,
            lineHeight = 22.sp * multiplier,
            letterSpacing = 0.2.sp
        )
        /* Other default text styles to override
        titleLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
        */
    )
}


@Composable
fun getFontMultiplier() : Float {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    return when{
        screenHeight < 1000 -> 1.0f
        screenHeight in 1001..1499 -> 2f
        else ->3f
    }
}