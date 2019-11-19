package nd.rw.kray

import kotlin.math.sqrt

class Tuple(val x: Double, val y: Double, val z: Double, val w: Double) {

    constructor(x: Number, y: Number, z: Number, w: Number)
            : this(x.toDouble(), y.toDouble(), z.toDouble(), w.toDouble())

    constructor(tupleAsList: List<Number>)
            : this(tupleAsList[0], tupleAsList[1], tupleAsList[2], tupleAsList[3])

    companion object {

        @JvmStatic
        fun point(x: Number, y: Number, z: Number): Tuple {
            return Tuple(x, y, z, 1.0)
        }

        @JvmStatic
        fun vector(x: Number, y: Number, z: Number): Tuple {
            return Tuple(x, y, z, 0.0)
        }
    }

    val normalize: Tuple
        get() {
            val magnitude = magnitude
            return Tuple(
                x = x / magnitude,
                y = y / magnitude,
                z = z / magnitude,
                w = w / magnitude
            )
        }

    val isPoint: Boolean
        get() = w == 1.0

    val isVector: Boolean
        get() = w == 0.0

    val magnitude: Double
        get() {
            return sqrt(
                x * x + y * y + z * z + w * w
            )
        }

    private val asList: List<Double>
        get() {
            return listOf(x, y, z, w)
        }

    operator fun get(i: Int): Double {
        //todo find a way to memoize this stuff?
        return asList[i]
    }

    operator fun plus(other: Tuple): Tuple {
        return Tuple(
            x = this.x + other.x,
            y = this.y + other.y,
            z = this.z + other.z,
            w = this.w + other.w
        )
    }

    operator fun minus(other: Tuple): Tuple {
        return Tuple(
            x = this.x - other.x,
            y = this.y - other.y,
            z = this.z - other.z,
            w = this.w - other.w
        )
    }

    operator fun unaryMinus(): Tuple {
        return Tuple(
            x = -this.x,
            y = -this.y,
            z = -this.z,
            w = -this.w
        )
    }

    operator fun times(scalar: Number): Tuple {
        return Tuple(
            x = this.x * scalar.toDouble(),
            y = this.y * scalar.toDouble(),
            z = this.z * scalar.toDouble(),
            w = this.w * scalar.toDouble()
        )
    }

    fun dot(other: Tuple): Double {
        return x * other.x +
                y * other.y +
                z * other.z +
                w * other.w
    }

    fun cross(other: Tuple): Tuple {
        return vector(
            y * other.z - z * other.y,
            z * other.x - x * other.z,
            x * other.y - y * other.x
        )
    }

    operator fun div(scalar: Number): Tuple {
        return Tuple(
            x = this.x / scalar.toDouble(),
            y = this.y / scalar.toDouble(),
            z = this.z / scalar.toDouble(),
            w = this.w / scalar.toDouble()
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != this.javaClass) return false

        other as Tuple

        return this.x.equalsWithMargin(other.x)
                && this.y.equalsWithMargin(other.y)
                && this.z.equalsWithMargin(other.z)
                && this.w.equalsWithMargin(other.w)
    }

    override fun toString(): String {
        return "Tuple(x=$x, y=$y, z=$z, w=$w)"
    }

    fun reflect(normal: Tuple): Tuple {
        return this - normal * 2 * this.dot(normal)
    }


}
