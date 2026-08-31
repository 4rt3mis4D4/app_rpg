package com.example.app_rpg.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    // Cor principal do Mestre
    primary = corMestrePrincipal,
    onPrimary = corBrancoPuro,

    // Cor de destaque do Mestre
    secondary = corMestreDestaque,
    onSecondary = corBrancoPuro,

    // Cor de destaque para ações do jogador
    tertiary = corJogadorPrincipal,
    onTertiary = corBrancoPuro,

    // Fundo geral
    background = corPretoPuro,
    onBackground = corBrancoOffWhite,

    // Superfícies: cards, painéis, modais
    surface = corCinzaEscuro,
    onSurface = corBrancoOffWhite,

    // Superfícies variantes
    surfaceVariant = corJogadorEscuro,
    onSurfaceVariant = corBrancoOffWhite,

    // Bordas e divisores
    outline = corCinzaMedio,

    // Erros/alertas
    error = corJogadorDestaque,
    onError = corPretoPuro
)

private val LightColorScheme = lightColorScheme(
    // Cor principal do Mestre
    primary = corMestrePrincipal,
    onPrimary = corBrancoPuro,

    // Cor de destaque do Mestre
    secondary = corMestreDestaque,
    onSecondary = corPretoPuro,

    // Cor principal das ações do jogador
    tertiary = corJogadorPrincipal,
    onTertiary = corBrancoPuro,

    // Fundo geral
    background = corCinzaClaro,
    onBackground = corPretoPuro,

    // Superfícies: cards, painéis, modais
    surface = corBrancoOffWhite,
    onSurface = corPretoPuro,

    // Superfícies variantes
    surfaceVariant = corCinzaClaro,
    onSurfaceVariant = corCinzaEscuro,

    // Bordas e divisores
    outline = corCinzaMedio,

    // Erros/alertas
    error = corJogadorDestaque,
    onError = corPretoPuro
)

@Composable
fun App_rpgTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
