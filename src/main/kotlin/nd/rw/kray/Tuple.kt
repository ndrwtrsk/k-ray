package nd.rw.kray

class Tuple(val x: Double, val y: Double, val z: Double, val w: Double) {

    constructor(x: Number, y: Number, z: Number, w: Number)
            : this(x.toDouble(), y.toDouble(), z.toDouble(), w.toDouble())

    companion object {

        @JvmStatic
        fun point(x: Double, y: Double, z: Double): Tuple {
            return Tuple(x, y, z, 1.0)
        }

        @JvmStatic
        fun vector(x: Double, y: Double, z: Double): Tuple {
            return Tuple(x, y, z, 0.0)
        }
    }

    val isPoint: Boolean
        get() = w == 1.0

    val isVector: Boolean
        get() = w == 0.0

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != this.javaClass) return false

        other as Tuple

        return this.x == other.x
                && this.y == other.y
                && this.z == other.z
                && this.w == other.w
    }

    operator fun times(scalar: Number): Tuple {
        return Tuple(
            x = this.x * scalar.toDouble(),
            y = this.y * scalar.toDouble(),
            z = this.z * scalar.toDouble(),
            w = this.w * scalar.toDouble()
        )
    }
}