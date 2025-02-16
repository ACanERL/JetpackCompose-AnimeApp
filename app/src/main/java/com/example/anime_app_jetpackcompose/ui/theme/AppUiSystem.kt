package com.example.anime_app_jetpackcompose.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp


//color
data class AppColorScheme(
    val backgroundColor:Color,
    val onBackgroundColor:Color,
    val primaryColor: Color,
    val onPrimaryColor:Color,
    val secondaryColor:Color,
    val onSecondaryColor:Color,
    val buttonColor:Color,
    val textColor: Color,
    val cardItemColor: Color,
)
//typography

data class AppTypography(
    val titleLarge:TextStyle,
    val titleMedium:TextStyle,
    val titleSmall:TextStyle,
    val body:TextStyle,
    val labelLarge:TextStyle,
    val labelMedium:TextStyle,
    val labelSmall:TextStyle
)
//shape
data class  AppShape(
    val button:Shape,
    val container:Shape
)
//size

data class AppSize(
    val large:Dp,
    val medium:Dp,
    val normal:Dp,
    val small:Dp,
)

val LocalAppColorScheme= staticCompositionLocalOf {
    AppColorScheme(
        backgroundColor = Color.Unspecified,
        onBackgroundColor = Color.Unspecified,
        primaryColor = Color.Unspecified,
        onPrimaryColor = Color.Unspecified,
        secondaryColor = Color.Unspecified,
        onSecondaryColor = Color.Unspecified,
        buttonColor = Color.Unspecified,
        textColor = Color.Unspecified,
        cardItemColor = Color.Unspecified

    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        titleLarge = TextStyle.Default,
        titleSmall = TextStyle.Default,
        titleMedium = TextStyle.Default,
        body = TextStyle.Default,
        labelLarge = TextStyle.Default,
        labelSmall = TextStyle.Default,
        labelMedium = TextStyle.Default
    )
}

val LocalAppShape= staticCompositionLocalOf {
    AppShape(
        button = RectangleShape,
        container = RectangleShape,
    )
}

val LocalAppSize= staticCompositionLocalOf {
    AppSize(
        large = Dp.Unspecified,
        medium = Dp.Unspecified,
        small = Dp.Unspecified,
        normal = Dp.Unspecified
    )
}