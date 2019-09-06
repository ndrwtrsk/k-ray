package nd.rw.kray

class Color(val red: Double, val green: Double, val blue: Double) {

    constructor(red: Number, green: Number, blue: Number)
            : this(red.toDouble(), green.toDouble(), blue.toDouble())

    operator fun plus(other: Color): Any {
        return Color(
            red = red + other.red,
            green = green + other.green,
            blue = blue + other.blue
        )
    }



    override fun toString(): String {
        return "Color(r=$red, g=$green, b=$blue)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Color

        if (red != other.red) return false
        if (green != other.green) return false
        if (blue != other.blue) return false

        return true
    }
}