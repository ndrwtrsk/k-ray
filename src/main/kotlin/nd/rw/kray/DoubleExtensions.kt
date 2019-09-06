package nd.rw.kray

import kotlin.math.abs

fun Double.equalsWithMargin(other: Double): Boolean {
    val epsilon = 0.00001
    return abs(this - other) < epsilon
}