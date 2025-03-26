package com.likhit.cryptotracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.likhit.cryptotracker.R

val IBMPlexMono = FontFamily(
    Font(
        resId = R.font.ibm_plex_mono_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.ibm_plex_mono_italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic
    ),
    Font(
        resId = R.font.ibm_plex_mono_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.ibm_plex_mono_bold_italic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic
    ),
    Font(
        resId = R.font.ibm_plex_mono_semi_bold,
        weight = FontWeight.SemiBold
    ),
    Font(
        resId = R.font.ibm_plex_mono_semi_bold_itallic,
        weight = FontWeight.SemiBold,
        style = FontStyle.Italic
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = IBMPlexMono,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = IBMPlexMono,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = IBMPlexMono,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontFamily = IBMPlexMono,
        fontWeight = FontWeight.Normal,
    ),
    headlineMedium = TextStyle(
        fontFamily = IBMPlexMono,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
)