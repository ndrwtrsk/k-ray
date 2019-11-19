package nd.rw.kray

import kotlin.math.roundToInt

class Color(val red: Double, val green: Double, val blue: Double) {

    constructor(red: Number, green: Number, blue: Number)
            : this(red.toDouble(), green.toDouble(), blue.toDouble())

    companion object {
        val black: Color = Color(0, 0, 0)
    }

    operator fun plus(other: Color): Color {
        return Color(
            red = red + other.red,
            green = green + other.green,
            blue = blue + other.blue
        )
    }

    operator fun minus(other: Color): Color {
        return Color(
            red = red - other.red,
            green = green - other.green,
            blue = blue - other.blue
        )
    }

    operator fun times(scalar: Number): Color {
        return Color(
            red = red * scalar.toDouble(),
            green = green * scalar.toDouble(),
            blue = blue * scalar.toDouble()
        )
    }

    operator fun times(other: Color): Color {
        return Color(
            red = red * other.red,
            green = green * other.green,
            blue = blue * other.blue
        )
    }

    fun toRgb(): String {
        val rgbRange = 0..255
        val redValue = (red * 255).roundToInt().coerceIn(rgbRange)
        val greenValue = (green * 255).roundToInt().coerceIn(rgbRange)
        val blueValue = (blue * 255).roundToInt().coerceIn(rgbRange)
        return "$redValue $greenValue $blueValue"
    }

    override fun toString(): String {
        return "Color(r=$red, g=$green, b=$blue)"
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Color

        if (!red.equalsWithMargin(other.red)) return false
        if (!green.equalsWithMargin(other.green)) return false
        if (!blue.equalsWithMargin(other.blue)) return false

        return true
    }
}


