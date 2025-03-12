package com.samuel.treinaiappcompose.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samuel.treinaiappcompose.R

val primaryFontFamilyBold = FontFamily(
  Font(R.font.inter_bold)
)
val primaryFontFamilySemiBold = FontFamily(
  Font(R.font.inter_semi_bold)
)

val primaryFontFamilyRegular = FontFamily(
  Font(R.font.inter_regular)
)

val Typography = Typography(
  titleLarge = TextStyle(
    fontFamily = primaryFontFamilyBold,
    fontSize = 24.sp,
    letterSpacing = 0.5.sp,
  ),
  titleMedium = TextStyle(
    fontFamily = primaryFontFamilySemiBold,
    fontSize = 16.sp
  ),
  titleSmall = TextStyle(
    fontFamily = primaryFontFamilyRegular,
    fontSize = 16.sp,
    letterSpacing = 0.5.sp
  ),
  labelMedium = TextStyle(
    fontFamily = primaryFontFamilyRegular,
    fontSize = 16.sp,
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

