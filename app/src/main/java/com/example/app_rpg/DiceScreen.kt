package com.example.app_rpg

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


private data class DiceStats(
    val expression: DiceExpression,
    val outcomes: List<Outcome>
) {
    val median: Int = outcomes.median()
    val bars: List<Bar> = outcomes.map { outcome ->
        Bar(
            label = outcome.total.toString(),
            value = (outcome.probability * 100).toFloat(),
            highlighted = outcome.total == median
        )
    }
}

@Composable
fun DiceScreen(modifier: Modifier = Modifier) {
    val padding = 16.dp
    val aspectRatio = 1.5f

    val expressionState = rememberTextFieldState()

    var stats by remember { mutableStateOf<DiceStats?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    fun submit() {
        parseDiceExpression(expressionState.text.toString())
            .onSuccess { expression ->
                stats = DiceStats(expression, expression.distribution())
                error = null
            }
            .onFailure { failure ->
                stats = null
                error = failure.message
            }
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(padding)
    ) {
        Text(
            text = "Dados",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(padding),
            verticalAlignment = Alignment.Top
        ) {
            TextField(
                modifier = Modifier.weight(4f),
                state = expressionState,
                label = { Text("Expressão") },
                placeholder = { Text("2d6 + 3") },
                lineLimits = TextFieldLineLimits.SingleLine,
                isError = error != null,
                supportingText = error?.let { message -> { Text(message) } }
            )

            Button(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                onClick = ::submit
            ) {
                Text("=")
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(padding)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(padding)) {
                BoxComponent(
                    label = "Média",
                    value = stats?.let { "%.2f".format(it.expression.mean) },
                    modifier = Modifier.weight(1f).aspectRatio(aspectRatio)
                )
                BoxComponent(
                    label = "Mediana",
                    value = stats?.median?.toString(),
                    modifier = Modifier.weight(1f).aspectRatio(aspectRatio)
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(padding)) {
                BoxComponent(
                    label = "Mín",
                    value = stats?.expression?.min?.toString(),
                    modifier = Modifier.weight(1f).aspectRatio(aspectRatio)
                )
                BoxComponent(
                    label = "Máx",
                    value = stats?.expression?.max?.toString(),
                    modifier = Modifier.weight(1f).aspectRatio(aspectRatio)
                )
            }
        }

        stats?.let { current ->
            BarChart(
                data = current.bars,
                modifier = Modifier.fillMaxWidth(),
                formatValue = { percent -> "%.0f".format(percent) }
            )
        }
    }
}

@Composable
fun BoxComponent(
    label: String,
    value: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .border(
                1.dp,
                MaterialTheme.colorScheme.outline,
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = value ?: "—",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )
        }
    }
}

data class Bar(
    val label: String,
    val value: Float,
    val highlighted: Boolean = false
)

@Composable
fun BarChart(
    data: List<Bar>,
    modifier: Modifier = Modifier,
    chartHeight: Dp = 180.dp,
    barColor: Color = MaterialTheme.colorScheme.primary,
    highlightColor: Color = MaterialTheme.colorScheme.tertiary,
    formatValue: (Float) -> String = { "%.1f".format(it) }
) {
    if (data.isEmpty()) return

    val peak = data.maxOfOrNull { it.value }?.takeIf { it > 0f } ?: 1f
    val showValues = data.size <= 9

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        data.forEach { bar ->
            key(bar.label) {
                val fraction by animateFloatAsState(
                    targetValue = bar.value / peak,
                    label = "barHeight"
                )

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (showValues) {
                        Text(
                            text = formatValue(bar.value),
                            style = MaterialTheme.typography.labelSmall,
                            maxLines = 1
                        )
                        Spacer(Modifier.height(4.dp))
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(chartHeight * fraction)
                            .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                            .background(if (bar.highlighted) highlightColor else barColor)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = bar.label,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

data class DiceExpression(
    val count: Int,
    val sides: Int,
    val modifier: Int = 0
) {
    val min: Int get() = count + modifier
    val max: Int get() = count * sides + modifier
    val mean: Double get() = count * (sides + 1) / 2.0 + modifier

    override fun toString(): String = buildString {
        append(count).append('d').append(sides)
        when {
            modifier > 0 -> append(" + ").append(modifier)
            modifier < 0 -> append(" - ").append(-modifier)
        }
    }
}

data class Outcome(val total: Int, val probability: Double)

private val DICE_REGEX = Regex("""^\s*(\d+)?\s*[dD]\s*(\d+)\s*(?:([+-])\s*(\d+)\s*)?$""")

private const val MAX_DICE = 100
private const val MAX_SIDES = 1000

fun parseDiceExpression(input: String): Result<DiceExpression> {
    val match = DICE_REGEX.matchEntire(input)
        ?: return fail("Expressão inválida. Use algo como \"2d6 + 3\".")

    val (countText, sidesText, sign, constantText) = match.destructured

    val count = countText.ifEmpty { "1" }.toIntOrNull()
        ?: return fail("Quantidade de dados grande demais.")

    val sides = sidesText.toIntOrNull()
        ?: return fail("Número de faces grande demais.")

    if (count !in 1..MAX_DICE) return fail("A quantidade de dados deve estar entre 1 e $MAX_DICE.")
    if (sides !in 2..MAX_SIDES) return fail("O dado deve ter entre 2 e $MAX_SIDES faces.")

    val modifier = if (constantText.isEmpty()) 0 else {
        val magnitude = constantText.toIntOrNull() ?: return fail("Modificador grande demais.")
        if (sign == "-") -magnitude else magnitude
    }

    return Result.success(DiceExpression(count, sides, modifier))
}

private fun fail(message: String): Result<DiceExpression> =
    Result.failure(IllegalArgumentException(message))

fun DiceExpression.distribution(): List<Outcome> {
    val p = 1.0 / sides
    var probs = doubleArrayOf(1.0)

    repeat(count) {
        val next = DoubleArray(probs.size + sides)
        for (i in probs.indices) {
            val carried = probs[i]
            if (carried == 0.0) continue
            for (face in 1..sides) {
                next[i + face] += carried * p
            }
        }
        probs = next
    }

    return (count..count * sides).map { raw ->
        Outcome(total = raw + modifier, probability = probs[raw])
    }
}

private const val EPSILON = 1e-9

fun List<Outcome>.median(): Int {
    require(isNotEmpty()) { "Cannot take the median of an empty distribution." }

    var cumulative = 0.0
    for (outcome in this) {
        cumulative += outcome.probability
        if (cumulative >= 0.5 - EPSILON) return outcome.total
    }
    return last().total
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DiceScreenPreview() {
    DiceScreen(Modifier.fillMaxSize())
}
